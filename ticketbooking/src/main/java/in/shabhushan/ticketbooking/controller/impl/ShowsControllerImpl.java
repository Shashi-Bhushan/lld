package in.shabhushan.ticketbooking.controller.impl;

import in.shabhushan.ticketbooking.controller.api.ShowsController;
import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.models.Show;
import in.shabhushan.ticketbooking.service.api.ShowsService;
import in.shabhushan.ticketbooking.util.ShowsCache;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ShowsControllerImpl implements ShowsController {

    @Autowired
    private ShowsService showsService;

    @Value("${com.ticketbooking.shows.cache.size}")
    private int showsCacheSize;

    @Value("${com.ticketbooking.shows.cache.durationSeconds}")
    private int showsCacheDuration;

    private final ShowsCache showsCache = ShowsCache.instance(showsService, showsCacheSize, showsCacheDuration);

    @Override
    public List<Show> getShowsByCity(ShowRequestDTO movieRequest) {
        increaseCount(movieRequest.getCity().name(), movieRequest.getMovieName());
        try {
            return showsCache.getShows(movieRequest);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void increaseCount(String city, String movie) {
        // Counter class stores the measurement name and the tags and their values
        Counter counter = Metrics.counter("request.orders",  "city", city, "movie", movie);
        counter.increment();
    }
}
