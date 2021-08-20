package in.shabhushan.ticketbooking.exceptions.accounts.exp;

import in.shabhushan.ticketbooking.exceptions.accounts.AccountsException;

public class InvalidCustomerException extends AccountsException {
    public InvalidCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
