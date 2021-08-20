package in.shabhushan.ticketbooking.controller.impl;

import in.shabhushan.ticketbooking.controller.api.BookingsController;
import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.models.Booking;
import in.shabhushan.ticketbooking.service.api.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingsControllerImpl implements BookingsController {

    @Autowired
    private BookingService bookingService;

    @Override
    public Booking createBooking(Long customer, BookingRequestDTO bookingRequest) {
        // validate Request first

        return bookingService.createBooking(customer, bookingRequest);
    }

    @Override
    public Booking cancelBooking(Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }
}
