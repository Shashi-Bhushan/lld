package in.shabhushan.ticketbooking.controller.api;

import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.models.Booking;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookingsController {
    @PostMapping(path = "/api/v1/bookings/customer/{customerId}")
    public Booking createBooking(
            @PathVariable(value = "customerId") Long customer,
            @RequestBody BookingRequestDTO bookingRequest);

    @DeleteMapping(path = "/api/v1/bookings/{bookingId}")
    public Booking cancelBooking(
            @PathVariable(value = "bookingId") Long bookingId
    );
}
