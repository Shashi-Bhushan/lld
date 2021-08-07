package in.shabhushan.lld.expense.repository;

import in.shabhushan.lld.expense.entity.Group;

public interface GroupRepository {
    boolean createGroup(Group group);
    Group getGroupByID(int groupId);
}
