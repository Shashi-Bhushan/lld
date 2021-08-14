package in.shabhushan.ticketbooking;

import in.shabhushan.ticketbooking.enums.Language;
import in.shabhushan.ticketbooking.models.Movie;
import in.shabhushan.ticketbooking.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class TicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingApplication.class, args);
	}

	public void run(String... args) {
		System.out.println("Starting application");


	}

}
