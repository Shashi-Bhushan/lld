package in.shabhushan.ticketbooking.controller.impl;

import in.shabhushan.ticketbooking.controller.api.BookingsController;
import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.exceptions.bookings.exp.BookingDoesNotExistException;
import in.shabhushan.ticketbooking.exceptions.bookings.exp.InvalidBookingStateException;
import in.shabhushan.ticketbooking.exceptions.bookings.exp.SeatNotAvailableException;
import in.shabhushan.ticketbooking.models.Booking;
import in.shabhushan.ticketbooking.repository.BookingsRepository;
import in.shabhushan.ticketbooking.service.api.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static in.shabhushan.ticketbooking.util.BookingRequestUtil.isAnySeatOccupiedAlready;
import static in.shabhushan.ticketbooking.util.BookingRequestUtil.isNotAcceptingBookings;

@RestController
public class BookingsControllerImpl implements BookingsController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Override
    public Booking createBooking(Long customer, BookingRequestDTO bookingRequest) {
        // validate Request first
        validateBookingRequest(bookingRequest);

        return bookingService.createBooking(customer, bookingRequest);
    }

    @Override
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingsRepository.findById(bookingId).orElseThrow(() -> new BookingDoesNotExistException("Booking does not exist for " + bookingId));

        validateBooking(bookingId, booking);

        return bookingService.cancelBooking(booking);
    }

    private void validateBooking(Long bookingId, Booking booking) {
        if (!booking.getShow().isNotStartedOrCancelled()) {
            throw new InvalidBookingStateException("The show with booking id " + bookingId + " has already started or cancelled.");
        }
    }

    private void validateBookingRequest(BookingRequestDTO bookingRequest) {
        if (isNotAcceptingBookings(bookingRequest)) {
            throw new SeatNotAvailableException("The show is not accepting bookings anymore.");
        }

        if (isAnySeatOccupiedAlready(bookingRequest)) {
            throw new SeatNotAvailableException("Some of the seats are not available for booking anymore.");
        }
    }
}
