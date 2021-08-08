package in.shabhushan.lld.expense.repository;

import in.shabhushan.lld.expense.entity.Group;
import in.shabhushan.lld.expense.entity.User;

import java.util.Set;

public interface GroupRepository {
    boolean createGroup(Group group);
    Group getGroupByID(int groupId);
    Set<Group> getAllGroupsByID(Set<Integer> idSet);
    Set<Group> getAllGroupsByUser(User user);
}
