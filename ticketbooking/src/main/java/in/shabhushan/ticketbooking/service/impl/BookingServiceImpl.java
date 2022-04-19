package in.shabhushan.ticketbooking.service.impl;

import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.enums.BookingStatus;
import in.shabhushan.ticketbooking.enums.ShowSeatStatus;
import in.shabhushan.ticketbooking.exceptions.bookings.exp.BookingDoesNotExistException;
import in.shabhushan.ticketbooking.exceptions.bookings.exp.InvalidBookingStateException;
import in.shabhushan.ticketbooking.models.Booking;
import in.shabhushan.ticketbooking.models.users.Customer;
import in.shabhushan.ticketbooking.repository.BookingsRepository;
import in.shabhushan.ticketbooking.repository.ShowSeatsRepository;
import in.shabhushan.ticketbooking.service.api.BookingService;
import in.shabhushan.ticketbooking.service.api.CustomersRepository;
import in.shabhushan.ticketbooking.service.api.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Booking createBooking(Long customerId, BookingRequestDTO bookingRequest) {
        Customer customer = customersRepository.getById(customerId);

        Booking booking = new Booking(customer, BookingStatus.PAYMENT_PENDING, bookingRequest.getShow());
        booking.setShowSeats(bookingRequest.getShowSeats());

        EntityTransaction utx = entityManager.getTransaction();

        try {
            utx.begin();

            saveShowSeats(bookingRequest);

            bookingsRepository.save(booking);

            utx.commit();
        } catch (Exception exception) {
            utx.rollback();
            throw exception;
        }

        return booking;
    }

    private void saveShowSeats(BookingRequestDTO bookingRequest) {
        bookingRequest.getShowSeats().forEach(showSeat -> {
            showSeat.setSeatStatus(ShowSeatStatus.OCCUPIED);
            //showSeat.setBooking(booking);
            showSeatsRepository.save(showSeat);
        });
    }

    @Transactional
    @Override
    public Booking cancelBooking(Booking booking) {
        booking.setStatus(BookingStatus.CANCELLED);

        if (booking.getStatus().equals(BookingStatus.CONFIRMED)) {
            refundService.refundBooking(booking);
        }

        booking.getShowSeats().forEach(showSeat -> {
            showSeat.setSeatStatus(ShowSeatStatus.VACANT);
            showSeat.setBooking(null);
            showSeatsRepository.save(showSeat);
        });

        booking.setShowSeats(null);
        bookingsRepository.save(booking);

        return booking;
    }
}
