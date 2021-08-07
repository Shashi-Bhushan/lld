package in.shabhushan.lld.expense.repository;

import in.shabhushan.lld.expense.entity.User;

import java.util.Set;

public interface UserRepository {
    boolean createUser(User user);
    User getUserByID(int userId);
    Set<User> getAllUsersByID(Set<Integer> idSet);
}
