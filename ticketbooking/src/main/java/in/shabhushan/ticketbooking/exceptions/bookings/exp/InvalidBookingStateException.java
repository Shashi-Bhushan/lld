package in.shabhushan.ticketbooking.exceptions.bookings.exp;

import in.shabhushan.ticketbooking.exceptions.bookings.BookingsException;

public class InvalidBookingStateException extends BookingsException {
    public InvalidBookingStateException(String message) {
        super(message);
    }

    public InvalidBookingStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
