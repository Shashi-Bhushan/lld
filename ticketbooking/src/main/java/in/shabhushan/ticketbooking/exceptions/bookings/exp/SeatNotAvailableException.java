package in.shabhushan.ticketbooking.exceptions.bookings.exp;

import in.shabhushan.ticketbooking.exceptions.bookings.BookingsException;

public class SeatNotAvailableException extends BookingsException {

    public SeatNotAvailableException(String message) {
        super(message);
    }

    public SeatNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
