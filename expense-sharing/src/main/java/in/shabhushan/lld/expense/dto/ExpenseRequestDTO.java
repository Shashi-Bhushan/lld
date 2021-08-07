package in.shabhushan.lld.expense.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

@Data
public class ExpenseRequestDTO {
    @NonNull
    private String name;

    @NonNull
    private String desc;

    @NonNull @Singular
    private Set<Integer> participants;

    @NonNull
    private Integer creator;

    @NonNull
    private Integer amount;
}
