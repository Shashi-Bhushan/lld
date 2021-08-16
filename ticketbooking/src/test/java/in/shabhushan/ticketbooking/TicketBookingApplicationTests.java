package in.shabhushan.ticketbooking;

import in.shabhushan.ticketbooking.controller.api.MoviesController;
import in.shabhushan.ticketbooking.dto.MovieRequestDTO;
import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.enums.Language;
import in.shabhushan.ticketbooking.models.Movie;
import in.shabhushan.ticketbooking.repository.MoviesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = "classpath:data.sql")
class TicketBookingApplicationTests {

	@Autowired
	private MoviesController moviesController;

	@Test
	void contextLoads() {
		List<Movie> moviesByCity = moviesController.getMoviesByCity(new MovieRequestDTO("The Matrix", City.BANGALORE));
		assertEquals(4, moviesByCity.size());

		moviesByCity = moviesController.getMoviesByCity(new MovieRequestDTO("Bruce Almighty", City.BANGALORE));
		assertEquals(6, moviesByCity.size());

		moviesByCity = moviesController.getMoviesByCity(new MovieRequestDTO("Bruce Almighty", City.MUMBAI));
		assertEquals(0, moviesByCity.size());
	}

}
