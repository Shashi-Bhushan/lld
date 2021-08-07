package in.shabhushan.lld.expense.strategy.impl;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.strategy.SplitStrategy;

import java.util.Map;
import java.util.Set;

public class EqualSplitPercentageStrategy implements SplitStrategy {
    @Override
    public void calculateAmountOwed(Expense expense) {
        Set<User> participants = expense.getParticipants();
        int totalAmount = expense.getTotalAmount();

        double equalAmount = totalAmount * 1.0d / participants.size();

        for (User user: participants) {
            Double paid = expense.getAmountPaid().get(user);

            expense.getAmountOwed().put(user, equalAmount - paid);
        }
    }
}
