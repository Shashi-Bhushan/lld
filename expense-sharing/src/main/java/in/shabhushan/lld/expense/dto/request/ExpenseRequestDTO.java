package in.shabhushan.lld.expense.dto.request;

import in.shabhushan.lld.expense.entity.Group;
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

    @Singular
    private Set<Integer> participants;

    @Singular
    private Group group;

    @NonNull
    private Integer creator;

    @NonNull
    private Integer amount;
}
