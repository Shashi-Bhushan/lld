package in.shabhushan.lld.expense.strategy.impl;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.strategy.PaymentStrategy;

import javax.ws.rs.NotSupportedException;
import java.util.Map;
import java.util.Set;

/**
 * In multi pay payment strategy, multiple users will be paying amount.
 * Each user who paid, paid what amount can be found by Amount Paid Map
 */
public class MultiPayPaymentStrategy implements PaymentStrategy {
    @Override
    public void calculatePaidAmount(Expense expense, Map<User, Double> amountPaid) {
        Set<User> participants = expense.getParticipants();

        for (User user: participants) {
            expense.getAmountPaid().put(user, amountPaid.getOrDefault(user, 0.0));
        }
    }
}
