package in.shabhushan.ticketbooking.dto;

import in.shabhushan.ticketbooking.enums.City;
import lombok.Data;
import lombok.NonNull;

@Data
public class ShowRequestDTO {
    @NonNull
    private String movieName;

    @NonNull
    private City city;
}
