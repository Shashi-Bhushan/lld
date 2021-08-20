package in.shabhushan.ticketbooking.exceptions.bookings;

import in.shabhushan.ticketbooking.exceptions.TicketBookingException;

public class BookingsException extends TicketBookingException {

    public BookingsException(String message) {
        super(message);
    }

    public BookingsException(String message, Throwable cause) {
        super(message, cause);
    }
}
