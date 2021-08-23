package in.shabhushan.ticketbooking;

import in.shabhushan.ticketbooking.controller.api.ShowsController;
import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.enums.City;
import in.shabhushan.ticketbooking.models.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = "classpath:data.sql")
class TicketBookingApplicationTests {

	@Autowired
	private ShowsController moviesController;

	@Test
	void contextLoads() {
//		List<Movie> moviesByCity = moviesController.getShowsByCity(new ShowRequestDTO("The Matrix", City.BANGALORE));
//		assertEquals(4, moviesByCity.size());
//
//		moviesByCity = moviesController.getShowsByCity(new ShowRequestDTO("Bruce Almighty", City.BANGALORE));
//		assertEquals(6, moviesByCity.size());
//
//		moviesByCity = moviesController.getShowsByCity(new ShowRequestDTO("Bruce Almighty", City.MUMBAI));
//		assertEquals(0, moviesByCity.size());
	}

}
