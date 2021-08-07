package in.shabhushan.lld.expense.repository.impl;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.repository.ExpenseRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final Map<Integer, Expense> expenses = new HashMap<>();

    @Override
    public boolean createExpense(Expense expense) {
        if (getExpenseByID(expense.getId()) == null) {
            expenses.put(expense.getId(), expense);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Expense getExpenseByID(int expenseId) {
        return expenses.get(expenseId);
    }

    @Override
    public Set<Expense> getAllExpensesByID(Set<Integer> idSet) {
        return idSet.stream().map(expenses::get).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @Override
    public Set<Expense> getAllExpensesByUser(User user) {
        Set<Expense> set = new HashSet<>();

        for (Map.Entry<Integer, Expense> entry: expenses.entrySet()) {
            if (entry.getValue().getParticipants().contains(user)) {
                set.add(entry.getValue());
            }
        }
        return set;
    }
}
