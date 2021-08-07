package in.shabhushan.lld.expense.strategy.impl;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.strategy.SplitStrategy;

import java.util.Map;
import java.util.Set;

public class SplitByPercentageStrategy implements SplitStrategy {
    private final Map<User, Double> percentage;

    public SplitByPercentageStrategy(Map<User, Double> percentage) {
        validatePercentage(percentage);

        this.percentage = percentage;
    }

    private static void validatePercentage(Map<User, Double> percentage) {
        double sum = percentage.values().stream().reduce(Double::sum).get();

        if (sum != 1.0d) {
            throw new RuntimeException("Percentage values does not equate to 100");
        }
    }

    @Override
    public void calculateAmountOwed(Expense expense) {
        Set<User> participants = expense.getParticipants();
        int totalAmount = expense.getTotalAmount();

        for (User user: participants) {
            double amount = percentage.get(user) * totalAmount;
            expense.getAmountOwed().put(user, amount);
        }
    }
}
