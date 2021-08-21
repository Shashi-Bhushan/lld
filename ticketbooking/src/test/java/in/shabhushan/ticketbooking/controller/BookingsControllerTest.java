package in.shabhushan.ticketbooking.controller;

import in.shabhushan.ticketbooking.controller.api.BookingsController;
import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.enums.ShowSeatStatus;
import in.shabhushan.ticketbooking.models.Cinema;
import in.shabhushan.ticketbooking.models.Hall;
import in.shabhushan.ticketbooking.models.Movie;
import in.shabhushan.ticketbooking.models.Seat;
import in.shabhushan.ticketbooking.models.Show;
import in.shabhushan.ticketbooking.models.ShowSeat;
import in.shabhushan.ticketbooking.repository.CinemasRepository;
import in.shabhushan.ticketbooking.repository.HallsRepository;
import in.shabhushan.ticketbooking.repository.MoviesRepository;
import in.shabhushan.ticketbooking.repository.SeatsRepository;
import in.shabhushan.ticketbooking.repository.ShowSeatsRepository;
import in.shabhushan.ticketbooking.repository.ShowsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Sql(scripts = "classpath:data.sql")
class BookingsControllerTest {

    @Autowired
    private BookingsController bookingsController;

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private SeatsRepository seatsRepository;

    @Autowired
    private ShowSeatsRepository showSeatsRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private CinemasRepository cinemasRepository;

    @Autowired
    private HallsRepository hallsRepository;

    @BeforeEach
    private void setup() {
        Show show = showsRepository.getById(1L);
        Seat seat = seatsRepository.getById(1L);

        ShowSeat showSeat = new ShowSeat();
        showSeat.setSeat(seat);
        showSeat.setShow(show);
        showSeat.setSeatStatus(ShowSeatStatus.VACANT);

        showSeatsRepository.save(showSeat);
    }

    @Test
    void test() {
        Cinema cinema = cinemasRepository.getById(1L);
        Hall hall = hallsRepository.getById(3L);
        Movie movie = moviesRepository.getById(9L);


        List<Show> shows = showsRepository.getShowByMovieAndHall(movie.getId(), hall.getId());

        System.out.println(shows);
        //bookingsController.createBooking(1L, new BookingRequestDTO());
    }
}
