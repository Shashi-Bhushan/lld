package in.shabhushan.lld.expense.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Group extends BaseEntity {
    private static int dbId = 0;

    private String name;
    private String desc;
    private Set<Expense> expenses;
    private User admin;
    private Set<User> users = new HashSet<>();

    public Group(String name, String desc, Set<Expense> expenses, User admin, Set<User> users) {
        super(dbId++);
        this.name = name;
        this.desc = desc;
        this.expenses = expenses;
        this.admin = admin;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public User getAdmin() {
        return admin;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Group group = (Group) o;
        return name.equals(group.name) && desc.equals(group.desc) && expenses.equals(group.expenses) && admin.equals(group.admin) && users.equals(group.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, desc, expenses, admin, users);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", expenses=" + expenses +
                ", admin=" + admin +
                ", users=" + users +
                '}';
    }
}
