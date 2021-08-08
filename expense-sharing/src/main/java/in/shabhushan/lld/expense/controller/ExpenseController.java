package in.shabhushan.lld.expense.controller;

import in.shabhushan.lld.expense.dto.AmountOwedResponseDTO;
import in.shabhushan.lld.expense.dto.ExpenseRequestDTO;
import in.shabhushan.lld.expense.dto.SettleUpResponse;
import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.Group;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.repository.ExpenseRepository;
import in.shabhushan.lld.expense.repository.UserRepository;
import in.shabhushan.lld.expense.service.ExpenseService;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@Path("/expense/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.expenseService = new ExpenseService(userRepository, expenseRepository);
    }


    public Expense createExpense(ExpenseRequestDTO expense, String splitStrategy, Map<User, Double> amountPaid, String paymentStrategy) {
        return expenseService.createExpense(expense, splitStrategy, amountPaid, paymentStrategy);
    }

    public List<SettleUpResponse> settleExpense(Expense expense) {
        return expenseService.settleExpense(expense);
    }

    public List<SettleUpResponse> settleExpense(User user) {
        return expenseService.settleExpense(user);
    }

    public List<SettleUpResponse> settleExpense(Group group) {
        //return expenseService.settleExpense(expense);
        return null;
    }

    public AmountOwedResponseDTO getAmountOwed(User user) {
        return expenseService.getAmountOwed(user);
    }
}
