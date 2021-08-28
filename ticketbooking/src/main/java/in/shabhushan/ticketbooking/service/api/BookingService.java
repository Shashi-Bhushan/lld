package in.shabhushan.ticketbooking.service.api;

import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.models.Booking;

public interface BookingService {
    Booking createBooking(Long customer, BookingRequestDTO bookingRequest);
    Booking cancelBooking(Booking booking);
}
