package in.shabhushan.ticketbooking.service.impl;

import in.shabhushan.ticketbooking.dto.MovieRequestDTO;
import in.shabhushan.ticketbooking.models.Movie;
import in.shabhushan.ticketbooking.repository.MoviesRepository;
import in.shabhushan.ticketbooking.service.api.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    public List<Movie> getMoviesByCity(MovieRequestDTO movieRequest) {
        return moviesRepository.getMoviesByCity(movieRequest.getCity().name(), movieRequest.getMovieName());
    }
}
