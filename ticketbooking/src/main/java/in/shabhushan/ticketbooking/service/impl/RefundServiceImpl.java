package in.shabhushan.ticketbooking.service.impl;

import in.shabhushan.ticketbooking.models.Booking;
import in.shabhushan.ticketbooking.service.api.RefundService;
import org.springframework.stereotype.Service;

@Service
public class RefundServiceImpl implements RefundService {
    @Override
    public void refundBooking(Booking booking) {
        System.out.println("Refunding money for booking " + booking);
    }
}
