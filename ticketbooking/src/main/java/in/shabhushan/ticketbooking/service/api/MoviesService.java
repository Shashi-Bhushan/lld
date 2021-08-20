package in.shabhushan.ticketbooking.service.api;

import in.shabhushan.ticketbooking.dto.MovieRequestDTO;
import in.shabhushan.ticketbooking.models.Movie;

import java.util.List;

public interface MoviesService {
    List<Movie> getMoviesByCity(MovieRequestDTO movieRequest);
}
