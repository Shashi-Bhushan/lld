package in.shabhushan.lld.expense.strategy.impl;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.strategy.PaymentStrategy;

import java.util.Map;
import java.util.Set;

/**
 * In Self Pay payment strategy, only one user will be paying the amount in the Expense.
 * So, other users will end up owing some amount to the creator
 */
public class SelfPayPaymentStrategy implements PaymentStrategy {

    @Override
    public void calculatePaidAmount(Expense expense, Map<User, Double> amountPaid) {
        User creator = expense.getCreator();
        Set<User> participants = expense.getParticipants();

        int totalAmount = expense.getTotalAmount();

        for (User user: participants) {
            if (user.equals(creator)) {
                expense.getAmountPaid().put(user, totalAmount * 1.0);
            } else {
                expense.getAmountPaid().put(user, 0.0);
            }
        }
    }
}
