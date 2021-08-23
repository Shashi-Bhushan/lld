package in.shabhushan.ticketbooking.controller.api;

import in.shabhushan.ticketbooking.dto.ShowRequestDTO;
import in.shabhushan.ticketbooking.models.Show;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ShowsController {

    @GetMapping("/api/v1/shows")
    public List<Show> getShowsByCity(@RequestBody(required = true) ShowRequestDTO movieRequest);
}
