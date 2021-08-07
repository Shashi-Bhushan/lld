package in.shabhushan.lld.expense.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User extends BaseEntity {

    private static int dbId = 1;

    private String name;
    private int phoneNumber;
    private String hashedPassword;
    private Set<Group> groups;
    private Set<Expense> expenses;

    public User(String name, int phoneNumber, String hashedPassword) {
        super(dbId);
        dbId++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.hashedPassword = hashedPassword;
        this.groups = new HashSet<>();
        this.expenses = new HashSet<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return phoneNumber == user.phoneNumber && name.equals(user.name) && hashedPassword.equals(user.hashedPassword) && groups.equals(user.groups) && expenses.equals(user.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, phoneNumber, hashedPassword, groups, expenses);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", groups=" + groups +
                ", expenses=" + expenses +
                '}';
    }
}
