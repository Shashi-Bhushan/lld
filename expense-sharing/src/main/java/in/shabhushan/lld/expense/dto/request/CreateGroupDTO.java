package in.shabhushan.lld.expense.dto.request;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.User;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

@Data
public class CreateGroupDTO {
    @NonNull
    private String name;

    @NonNull
    private String desc;

    @NonNull @Singular
    private Set<User> users;

    @NonNull @Singular
    private Set<Expense> expenses;

    @NonNull
    private User admin;
}
