package in.shabhushan.ticketbooking.service.impl;

import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.enums.BookingStatus;
import in.shabhushan.ticketbooking.exceptions.bookings.exp.BookingDoesNotExistException;
import in.shabhushan.ticketbooking.exceptions.bookings.exp.InvalidBookingStateException;
import in.shabhushan.ticketbooking.exceptions.bookings.exp.SeatNotAvailableException;
import in.shabhushan.ticketbooking.models.Booking;
import in.shabhushan.ticketbooking.models.ShowSeat;
import in.shabhushan.ticketbooking.models.users.Customer;
import in.shabhushan.ticketbooking.repository.BookingsRepository;
import in.shabhushan.ticketbooking.repository.ShowSeatsRepository;
import in.shabhushan.ticketbooking.service.api.BookingService;
import in.shabhushan.ticketbooking.service.api.CustomersRepository;
import in.shabhushan.ticketbooking.service.api.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ShowSeatsRepository showSeatsRepository;

    @Autowired
    private RefundService refundService;

    public Booking createBooking(Long customerId, BookingRequestDTO bookingRequest) {
        Customer customer = customersRepository.getById(customerId);

        if (!bookingRequest.getShow().isNotStartedOrCancelled()) {
            throw new SeatNotAvailableException("The show is not accepting bookings anymore.");
        }

        if (bookingRequest.getShowSeats().stream().anyMatch(ShowSeat::isOccupied)) {
            throw new SeatNotAvailableException("Some of the seats are not available for booking anymore.");
        }

        bookingRequest.getShowSeats().forEach(showSeat -> {
            showSeat.setOccupied(true);
            showSeatsRepository.save(showSeat);
        });

        Booking booking = new Booking(customer, BookingStatus.PAYMENT_PENDING, bookingRequest.getShow());
        booking.setShowSeats(bookingRequest.getShowSeats());

        bookingsRepository.save(booking);

        return booking;
    }

    @Override
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingsRepository.findById(bookingId).orElseThrow(() -> new BookingDoesNotExistException("Booking Does not exist for " + bookingId));

        if (!booking.getShow().isNotStartedOrCancelled()) {
            throw new InvalidBookingStateException("The show with booking id " + bookingId + " has already started or cancelled.");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        refundService.refundBooking(booking);

        booking.getShowSeats().forEach(showSeat -> {
            showSeat.setOccupied(false);
            showSeatsRepository.save(showSeat);
        });

        booking.setShowSeats(null);
        bookingsRepository.save(booking);

        return booking;
    }
}
