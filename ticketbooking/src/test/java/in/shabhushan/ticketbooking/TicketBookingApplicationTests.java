package in.shabhushan.ticketbooking;

import in.shabhushan.ticketbooking.enums.Language;
import in.shabhushan.ticketbooking.models.Movie;
import in.shabhushan.ticketbooking.repository.MoviesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.HashSet;

@SpringBootTest
@Sql(scripts = "classpath:data.sql")
class TicketBookingApplicationTests {

	@Autowired
	private MoviesRepository moviesRepository;

	@Test
	void contextLoads() {
		//moviesRepository.save(new Movie("h", 12, "", "", new Date(), Language.ENGLISH, new HashSet<>()));
	}

}
