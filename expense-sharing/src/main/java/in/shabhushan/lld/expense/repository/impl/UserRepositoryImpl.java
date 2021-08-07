package in.shabhushan.lld.expense.repository.impl;

import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * User In memory repository
 */
public class UserRepositoryImpl implements UserRepository {

    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public boolean createUser(User user) {
        if (getUserByID(user.getId()) == null) {
            users.put(user.getId(), user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUserByID(int userId) {
        return users.get(userId);
    }

    @Override
    public Set<User> getAllUsersByID(Set<Integer> idSet) {
        return idSet.stream().map(users::get).filter(Objects::nonNull).collect(Collectors.toSet());
    }
}
