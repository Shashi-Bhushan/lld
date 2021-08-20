package in.shabhushan.ticketbooking.dto;

import in.shabhushan.ticketbooking.models.Show;
import in.shabhushan.ticketbooking.models.ShowSeat;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class BookingRequestDTO {
    @NonNull
    private Show show;

    @NonNull
    private Set<ShowSeat> showSeats;
}
