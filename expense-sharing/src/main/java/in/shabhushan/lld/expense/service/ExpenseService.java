package in.shabhushan.lld.expense.service;

import in.shabhushan.lld.expense.dto.response.AmountOwedDTO;
import in.shabhushan.lld.expense.dto.request.ExpenseRequestDTO;
import in.shabhushan.lld.expense.dto.response.SettleUpResponse;
import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.Group;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.repository.ExpenseRepository;
import in.shabhushan.lld.expense.repository.UserRepository;
import in.shabhushan.lld.expense.strategy.PaymentStrategy;
import in.shabhushan.lld.expense.strategy.PaymentStrategyFactory;
import in.shabhushan.lld.expense.strategy.SplitStrategy;
import in.shabhushan.lld.expense.strategy.SplitStrategyFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ExpenseService {

    private final UserRepository userRepository;

    private final ExpenseRepository expenseRepository;

    public ExpenseService(UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    /**
     * Get User who created Expense
     * Get All participants
     * Get/Create Group and Total amount to be paid
     * <p>
     * Get amount paid by creator
     * Get amount owed by participants
     */
    public Expense createExpense(ExpenseRequestDTO expenseDTO, String splitStrategyTerm, Map<User, Double> amountPaid, String paymentStrategyTerm) {
        if (expenseDTO.getGroup() != null) {
            return createExpenseForGroup(expenseDTO, splitStrategyTerm, amountPaid, paymentStrategyTerm);
        } else {
            User creator = userRepository.getUserByID(expenseDTO.getCreator());
            Set<User> participants = userRepository.getAllUsersByID(expenseDTO.getParticipants());

            Expense expense = new Expense(expenseDTO.getName(), expenseDTO.getDesc(), expenseDTO.getAmount(), creator, participants);

            PaymentStrategy paymentStrategy = PaymentStrategyFactory.forName(paymentStrategyTerm);
            paymentStrategy.calculatePaidAmount(expense, amountPaid);

            SplitStrategy splitStrategy = SplitStrategyFactory.forName(splitStrategyTerm);
            splitStrategy.calculateAmountOwed(expense);

            expenseRepository.createExpense(expense);

            return expense;
        }
    }

    private Expense createExpenseForGroup(ExpenseRequestDTO expenseDTO, String splitStrategyTerm, Map<User, Double> amountPaid, String paymentStrategyTerm) {
        User creator = userRepository.getUserByID(expenseDTO.getCreator());
        Set<User> participants = expenseDTO.getGroup().getUsers();

        Expense expense = new Expense(expenseDTO.getName(), expenseDTO.getDesc(), expenseDTO.getAmount(), creator, participants);

        PaymentStrategy paymentStrategy = PaymentStrategyFactory.forName(paymentStrategyTerm);
        paymentStrategy.calculatePaidAmount(expense, amountPaid);

        SplitStrategy splitStrategy = SplitStrategyFactory.forName(splitStrategyTerm);
        splitStrategy.calculateAmountOwed(expense);

        expenseRepository.createExpense(expense);

        return expense;
    }

    public List<SettleUpResponse> settleExpense(User user) {
        Set<Expense> expenseHistory = getExpenseHistory(user);

        List<SettleUpResponse> output = new ArrayList<>();

        for (Expense expense : expenseHistory) {
            output.addAll(settleExpense(expense));
        }

        return output.stream().filter(a -> a.getSender().equals(user)).collect(Collectors.toList());
    }

    public List<SettleUpResponse> settleExpense(Group group) {
        Set<Expense> expenseHistory = group.getExpenses();

        List<SettleUpResponse> output = new ArrayList<>();

        for (Expense expense : expenseHistory) {
            output.addAll(settleExpense(expense));
        }

        return output.stream()
                // sender is user of group
                .filter(a -> group.getUsers().contains(a.getSender()))
                .collect(Collectors.toList());
    }

    public AmountOwedDTO getAmountOwed(User user) {
        // get All expenses this user is part of
        Set<Expense> expenseHistory = getExpenseHistory(user);

        Map<User, Double> creditors = new HashMap<>();

        for (Expense expense : expenseHistory) {
            if (!expense.isSettled()) {
                settleExpense(expense);
            }

            List<Map.Entry<User, Double>> creditor = expense.getCreditors().getOrDefault(user, new ArrayList<>());

            for (Map.Entry<User, Double> c : creditor) {
                creditors.put(c.getKey(), creditors.getOrDefault(c.getKey(), 0.0) + c.getValue());
            }
        }

        return new AmountOwedDTO(creditors);
    }

    public List<SettleUpResponse> settleExpense(Expense expense) {
        PriorityQueue<Map.Entry<User, Double>> minHeap = new PriorityQueue<>((a, b) -> a.getValue().compareTo(b.getValue()));
        PriorityQueue<Map.Entry<User, Double>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));

        Set<User> participants = expense.getParticipants();

        for (User user : participants) {
            Double owed = expense.getAmountOwed().get(user);

            if (owed < 0) {
                minHeap.add(Map.entry(user, owed));
            } else {
                maxHeap.add(Map.entry(user, owed));
            }
        }

        List<SettleUpResponse> settleUpResponses = new ArrayList<>();

        while (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            // this guy is to be given most amount
            Map.Entry<User, Double> receiver = minHeap.poll();
            // this guy owes the largest amount
            Map.Entry<User, Double> sender = maxHeap.poll();

            // amount left to be paid in this transaction (sender - receiver)
            Double receiverAmount = receiver.getValue();
            Double senderAmount = sender.getValue();

            User receiverUser = receiver.getKey();
            User senderUser = sender.getKey();

            if (Objects.equals(senderAmount, -1 * receiverAmount)) {
                settleUpResponses.add(new SettleUpResponse(receiverUser, senderUser, senderAmount)); // or -1 * receiverAmount
            } else if (-1 * receiverAmount > senderAmount) {
                // receiver still hasn't received full amount owed to him
                settleUpResponses.add(new SettleUpResponse(receiverUser, senderUser, senderAmount));

                minHeap.add(Map.entry(receiverUser, -1 * ((-1.0 * receiverAmount) - senderAmount)));
            } else if (senderAmount > -1 * receiverAmount) {
                // sender still has money that he can give
                settleUpResponses.add(new SettleUpResponse(receiverUser, senderUser, -1 * receiverAmount));

                maxHeap.add(Map.entry(senderUser, senderAmount - receiverAmount));
            }
        }

        if (!expense.isSettled()) {
            for (SettleUpResponse res : settleUpResponses) {
                if (!expense.getCreditors().containsKey(res.getSender())) {
                    expense.getCreditors().put(res.getSender(), new ArrayList<>());
                }

                expense.getCreditors().get(res.getSender()).add(Map.entry(res.getReceiver(), res.getAmount()));
            }

            expense.setSettled(true);
        }

        return settleUpResponses;
    }

    private Set<Expense> getExpenseHistory(User user) {
        // get All expenses this user is part of
        return expenseRepository.getAllExpensesByUser(user);
    }
}
