package in.shabhushan.ticketbooking.controller.impl;

import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.controller.api.MoviesController;
import in.shabhushan.ticketbooking.models.Movie;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoviesControllerImpl implements MoviesController {

    @Override
    public List<Movie> getMoviesByCity(City city) {
        return null;
    }
}
