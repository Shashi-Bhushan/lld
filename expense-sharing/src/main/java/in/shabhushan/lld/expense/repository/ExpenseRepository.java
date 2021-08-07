package in.shabhushan.lld.expense.repository;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.User;

import java.util.Set;

public interface ExpenseRepository {
    boolean createExpense(Expense expense);
    Expense getExpenseByID(int expenseId);
    public Set<Expense> getAllExpensesByID(Set<Integer> idSet);
    public Set<Expense> getAllExpensesByUser(User user);
}
