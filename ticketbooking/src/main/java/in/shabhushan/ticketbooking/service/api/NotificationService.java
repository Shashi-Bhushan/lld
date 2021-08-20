package in.shabhushan.ticketbooking.service.api;

import in.shabhushan.ticketbooking.models.users.User;

public interface NotificationService {
    void notifyRefundInitiated(User user, String phone, String message);
}
