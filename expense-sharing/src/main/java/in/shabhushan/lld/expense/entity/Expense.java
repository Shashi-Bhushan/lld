package in.shabhushan.lld.expense.entity;

import java.util.*;

public class Expense extends BaseEntity {
    private static int dbId = 1;

    private String name;
    private String desc;
    private int totalAmount;
    private User creator;
    private Set<User> participants;
    private Group group;
    private Map<User, Double> amountPaid = new HashMap<>();
    private Map<User, Double> amountOwed = new HashMap<>();

    // Map<User, List<Creditor, AmountOwed>>
    private Map<User, List<Map.Entry<User, Double>>> creditors = new HashMap<>();
    private boolean isSettled;


    public Expense(String name, String desc, int totalAmount, User creator, Set<User> participants) {
        super(dbId++);
        this.name = name;
        this.desc = desc;
        this.totalAmount = totalAmount;
        this.creator = creator;
        this.participants = participants;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public User getCreator() {
        return creator;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public Map<User, Double> getAmountPaid() {
        return amountPaid;
    }

    public Map<User, Double> getAmountOwed() {
        return amountOwed;
    }

    public Map<User, List<Map.Entry<User, Double>>> getCreditors() {
        return creditors;
    }

    public boolean isSettled() {
        return isSettled;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Expense expense = (Expense) o;
        return totalAmount == expense.totalAmount && name.equals(expense.name) && desc.equals(expense.desc) && creator.equals(expense.creator) && participants.equals(expense.participants) && Objects.equals(amountPaid, expense.amountPaid) && Objects.equals(amountOwed, expense.amountOwed) && Objects.equals(group, expense.group)  && Objects.equals(creditors, expense.creditors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, desc, totalAmount, creator, participants, amountPaid, amountOwed, creditors);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", totalAmount=" + totalAmount +
                ", creator=" + creator +
                ", participants=" + participants +
                ", group=" + group +
                ", amountPaid=" + amountPaid +
                ", amountOwed=" + amountOwed +
                ", creditors=" + creditors +
                '}';
    }
}
