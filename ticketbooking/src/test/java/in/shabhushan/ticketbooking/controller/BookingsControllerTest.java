package in.shabhushan.ticketbooking.controller;

import in.shabhushan.ticketbooking.controller.api.BookingsController;
import in.shabhushan.ticketbooking.controller.api.ShowsController;
import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.enums.BookingStatus;
import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.enums.SeatType;
import in.shabhushan.ticketbooking.enums.ShowSeatStatus;
import in.shabhushan.ticketbooking.models.Booking;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Autowired
    private ShowsController moviesController;

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private static final int SEAT_COUNT = 4;

    @BeforeEach
    private void setup() {
            for (long hallNumber = 1; hallNumber <= 16; hallNumber++) {
                Hall hall = hallsRepository.getById(hallNumber);

                for (int seatLocation = 1; seatLocation <= SEAT_COUNT; seatLocation++) {
                    Seat seat = new Seat();
                    seat.setSeatLocation(seatLocation);
                    seat.setUnderMaintainance(false);
                    seat.setSeatType(SeatType.TWO_D);
                    seat.setHall(hall);

                    seatsRepository.save(seat);
                }
            }

        for (long showNumber = 1; showNumber <= 16; showNumber++) {
            long hallNumber = ((showNumber - 1) % 4) + 1;

            Show show = showsRepository.getById(showNumber);
            Hall hall = hallsRepository.getById(hallNumber);

            for (Seat seat: hall.getSeats()) {
                ShowSeat showSeat = new ShowSeat();
                showSeat.setSeatStatus(ShowSeatStatus.VACANT);
                showSeat.setPrice(150);
                showSeat.setSeat(seat);
                showSeat.setShow(show);

                showSeatsRepository.save(showSeat);
            }
        }
    }

    @Test
    void test() {
        List<Show> shows = moviesController.getShowsByCity(new ShowRequestDTO("The Matrix", City.BANGALORE));

        System.out.println(shows);

        // get first 3 seats of first show
        Set<ShowSeat> desiredSeats = shows.get(0).getShowSeat().stream().limit(3).collect(Collectors.toSet());

        Booking booking = bookingsController.createBooking(1L, new BookingRequestDTO(shows.get(0), desiredSeats));

        assertEquals(shows.get(0), booking.getShow());
        assertEquals(desiredSeats, booking.getShowSeats());
        assertEquals(BookingStatus.PAYMENT_PENDING, booking.getStatus());
    }
}
