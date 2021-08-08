package in.shabhushan.lld.expense.repository.impl;

import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.Group;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.repository.ExpenseRepository;
import in.shabhushan.lld.expense.repository.GroupRepository;

import java.util.*;
import java.util.stream.Collectors;

public class GroupRepositoryImpl implements GroupRepository {
    private final Map<Integer, Group> groups = new HashMap<>();

    public boolean createGroup(Group group) {
        if (getGroupByID(group.getId()) == null) {
            groups.put(group.getId(), group);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Group getGroupByID(int groupId) {
        return groups.get(groupId);
    }

    @Override
    public Set<Group> getAllGroupsByID(Set<Integer> idSet) {
        return idSet.stream().map(groups::get).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @Override
    public Set<Group> getAllGroupsByUser(User user) {
        Set<Group> set = new HashSet<>();

        for (Map.Entry<Integer, Group> entry: groups.entrySet()) {
            if (entry.getValue().getUsers().contains(user)) {
                set.add(entry.getValue());
            }
        }

        return set;
    }
}
