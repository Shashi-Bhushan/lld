package in.shabhushan.ticketbooking.exceptions;

public class TicketBookingException extends RuntimeException {

    public TicketBookingException(String message) {
        super(message);
    }

    public TicketBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
