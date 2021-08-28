package in.shabhushan.ticketbooking.util;

import in.shabhushan.ticketbooking.dto.BookingRequestDTO;
import in.shabhushan.ticketbooking.enums.ShowSeatStatus;

public class BookingRequestUtil {
    private BookingRequestUtil() {}

    public static boolean isAnySeatOccupiedAlready(BookingRequestDTO bookingRequest) {
        return bookingRequest.getShowSeats().stream().anyMatch(showSeat -> showSeat.getSeatStatus().equals(ShowSeatStatus.OCCUPIED));
    }

    public static boolean isNotAcceptingBookings(BookingRequestDTO bookingRequest) {
        return !bookingRequest.getShow().isNotStartedOrCancelled();
    }
}
