package in.shabhushan.lld.expense.dto.response;

import in.shabhushan.lld.expense.entity.User;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;

@Data
public class AmountOwedDTO {
    @NonNull @Singular
    private Map<User, Double> creditors;
}
