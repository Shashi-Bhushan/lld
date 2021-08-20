package in.shabhushan.ticketbooking.exceptions.accounts.exp;

import in.shabhushan.ticketbooking.exceptions.accounts.AccountsException;

public class AccountAlreadyExistsException extends AccountsException {
    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
