package in.shabhushan.ticketbooking.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.models.Show;
import in.shabhushan.ticketbooking.service.api.ShowsService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ShowsCache {
    private static final Logger LOG = LoggerFactory.getLogger(ShowsCache.class);

    private static final ShowsCache instance = new ShowsCache();
    private static ShowsService showsService;
    private static LoadingCache<Map.Entry<String, String>, List<Show>> showsCache;

    private static final String CACHE_BUCKET = "shows.cache.metric";

    private static Counter getCount;
    private static Counter cacheMissCount;
    private static Counter removalCount;

    private ShowsCache() {}

    public static ShowsCache instance(ShowsService showsService, int size, int duration) {
        if (ShowsCache.showsService == null) {
            ShowsCache.init(showsService, size, duration);
        }

        return instance;
    }

    public static void init(ShowsService showsService, int size, int duration) {
        ShowsCache.showsService = showsService;
        ShowsCache.showsCache = initShowsCache(size, duration);

        initCounters();
    }

    private static void initCounters() {
        getCount = Counter.builder(CACHE_BUCKET)
                .tag("operation", "get")
                .register(Metrics.globalRegistry);

        cacheMissCount = Counter.builder(CACHE_BUCKET)
                .tag("operation", "cachemiss")
                .register(Metrics.globalRegistry);

        removalCount = Counter.builder(CACHE_BUCKET)
                .tag("operation", "remove")
                .register(Metrics.globalRegistry);
    }

    public List<Show> getShows(ShowRequestDTO showRequest) throws ExecutionException {
        List<Show> result = showsCache.get(Map.entry(showRequest.getMovieName(), showRequest.getCity().name()));

        getCount.increment();

        CacheStats stats = showsCache.stats();
        LOG.info("Cache Stats {}", stats);

        return result;
    }

    private static LoadingCache<Map.Entry<String, String>, List<Show>> initShowsCache(int size, int durationSeconds) {
        return CacheBuilder.newBuilder()
            .maximumSize(size)
            .expireAfterWrite(durationSeconds, TimeUnit.SECONDS)
            .removalListener((RemovalListener<Map.Entry<String, String>, List<Show>>) removalNotification -> removalCount.increment())
            .build(
                    new CacheLoader<>() {
                        @Override
                        @SuppressWarnings("NullableProblems")
                        public List<Show> load(Map.Entry<String, String> entry) {
                            ShowRequestDTO movieRequest = new ShowRequestDTO(entry.getKey(), City.forName(entry.getValue()));

                            cacheMissCount.increment();

                            LOG.info("Movie Request for {}", movieRequest);
                            return showsService.getMoviesByCity(movieRequest);
                        }
                    });
    }
}
