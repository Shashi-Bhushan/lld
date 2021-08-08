package in.shabhushan.lld.expense.dto.response;

import in.shabhushan.lld.expense.entity.User;
import lombok.Data;
import lombok.NonNull;

@Data
public class SettleUpResponse {
    @NonNull
    private User receiver;

    @NonNull
    private User sender;

    @NonNull
    private double amount;
}
