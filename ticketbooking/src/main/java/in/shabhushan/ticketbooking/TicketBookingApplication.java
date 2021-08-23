package in.shabhushan.ticketbooking;

import in.shabhushan.ticketbooking.service.api.ShowsService;
import in.shabhushan.ticketbooking.util.ShowsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TicketBookingApplication {

	@Autowired
	private ShowsService showsService;

	@Value("${com.ticketbooking.shows.cache.size}")
	private int showsCacheSize;

	@Value("${com.ticketbooking.shows.cache.durationSeconds}")
	private int showsCacheDuration;

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingApplication.class, args);
	}

	public void run(String... args) {
		System.out.println("Starting application");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initShowsCache() {
		ShowsCache.init(showsService, showsCacheSize, showsCacheDuration);
	}

}
