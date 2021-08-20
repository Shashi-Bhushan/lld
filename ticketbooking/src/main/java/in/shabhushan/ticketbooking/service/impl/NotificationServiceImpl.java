package in.shabhushan.ticketbooking.service.impl;

import in.shabhushan.ticketbooking.models.users.User;
import in.shabhushan.ticketbooking.service.api.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifyRefundInitiated(User user, String phone, String message) {

    }
}
