package in.shabhushan.lld.expense.strategy;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.User;

import java.util.Map;

public interface PaymentStrategy {
    void calculatePaidAmount(Expense expense, Map<User, Double> amountPaid);
}
