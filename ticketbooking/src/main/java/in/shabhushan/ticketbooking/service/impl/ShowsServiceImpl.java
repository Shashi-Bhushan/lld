package in.shabhushan.ticketbooking.service.impl;

import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.models.Movie;
import in.shabhushan.ticketbooking.models.Show;
import in.shabhushan.ticketbooking.repository.MoviesRepository;
import in.shabhushan.ticketbooking.repository.ShowsRepository;
import in.shabhushan.ticketbooking.service.api.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowsServiceImpl implements ShowsService {

    @Autowired
    private ShowsRepository moviesRepository;

    public List<Show> getMoviesByCity(ShowRequestDTO movieRequest) {
        return moviesRepository.getShowByMovieAndCity(movieRequest.getCity().name(), movieRequest.getMovieName());
    }
}
