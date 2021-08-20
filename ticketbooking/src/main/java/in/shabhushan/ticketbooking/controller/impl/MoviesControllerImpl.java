package in.shabhushan.ticketbooking.controller.impl;

import in.shabhushan.ticketbooking.dto.MovieRequestDTO;
import in.shabhushan.ticketbooking.controller.api.MoviesController;
import in.shabhushan.ticketbooking.models.Movie;
import in.shabhushan.ticketbooking.service.api.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoviesControllerImpl implements MoviesController {

    @Autowired
    private MoviesService moviesService;

    @Override
    public List<Movie> getMoviesByCity(MovieRequestDTO movieRequest) {
        return moviesService.getMoviesByCity(movieRequest);
    }
}
