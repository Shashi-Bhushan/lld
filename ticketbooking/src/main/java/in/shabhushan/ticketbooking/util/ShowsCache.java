package in.shabhushan.ticketbooking.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.models.Show;
import in.shabhushan.ticketbooking.service.api.ShowsService;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.binder.cache.GuavaCacheMetrics;
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
    }

    public List<Show> getShows(ShowRequestDTO showRequest) throws ExecutionException {
        LOG.info("Cache Stats {}", showsCache.stats());
        return showsCache.get(Map.entry(showRequest.getMovieName(), showRequest.getCity().name()));
    }

    private static LoadingCache<Map.Entry<String, String>, List<Show>> initShowsCache(int size, int durationSeconds) {
        LoadingCache<Map.Entry<String, String>, List<Show>> cache = CacheBuilder.newBuilder()
            .maximumSize(size)
            .expireAfterWrite(durationSeconds, TimeUnit.SECONDS)
            .recordStats()
            .build(
                new CacheLoader<Map.Entry<String, String>, List<Show>>() {
                    @Override
                    @SuppressWarnings("NullableProblems")
                    public List<Show> load(Map.Entry<String, String> entry) {
                        ShowRequestDTO movieRequest = new ShowRequestDTO(entry.getKey(), City.forName(entry.getValue()));

                        LOG.info("Movie Request for {}", movieRequest);
                        return showsService.getMoviesByCity(movieRequest);
                    }
                });

        return GuavaCacheMetrics.monitor(Metrics.globalRegistry, cache, "shows.cache.metrics");
    }
}
