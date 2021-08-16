package in.shabhushan.ticketbooking.controller.api;

import in.shabhushan.ticketbooking.dto.MovieRequestDTO;
import in.shabhushan.ticketbooking.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MoviesController {

    @GetMapping("/api/v1/movies")
    public List<Movie> getMoviesByCity(@RequestBody(required = true) MovieRequestDTO movieRequest);
}
