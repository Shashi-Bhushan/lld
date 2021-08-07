package in.shabhushan.lld.expense.strategy;

import in.shabhushan.lld.expense.entity.Expense;

public interface SplitStrategy {
    public void calculateAmountOwed(Expense expense);
}
