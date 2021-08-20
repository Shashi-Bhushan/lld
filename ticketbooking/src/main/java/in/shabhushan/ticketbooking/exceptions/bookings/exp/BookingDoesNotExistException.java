package in.shabhushan.ticketbooking.exceptions.bookings.exp;

import in.shabhushan.ticketbooking.exceptions.bookings.BookingsException;

public class BookingDoesNotExistException extends BookingsException {
    public BookingDoesNotExistException(String message) {
        super(message);
    }

    public BookingDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
