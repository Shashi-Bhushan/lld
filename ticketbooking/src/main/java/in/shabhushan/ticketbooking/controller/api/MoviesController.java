package in.shabhushan.ticketbooking.controller.api;

import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MoviesController {

    @GetMapping("/api/v1/movies/city/{city}")
    public List<Movie> getMoviesByCity(@PathVariable(name = "city") City city);
}
