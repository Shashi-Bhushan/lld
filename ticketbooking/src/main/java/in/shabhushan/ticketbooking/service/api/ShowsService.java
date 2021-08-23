package in.shabhushan.ticketbooking.service.api;

import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.models.Movie;
import in.shabhushan.ticketbooking.models.Show;

import java.util.List;

public interface ShowsService {
    List<Show> getMoviesByCity(ShowRequestDTO movieRequest);
}
