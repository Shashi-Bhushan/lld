package in.shabhushan.lld.expense.service;

import in.shabhushan.lld.expense.dto.request.CreateGroupDTO;
import in.shabhushan.lld.expense.entity.Group;
import in.shabhushan.lld.expense.repository.GroupRepository;

public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(CreateGroupDTO createGroup) {
        Group group = new Group(createGroup.getName(), createGroup.getDesc(), createGroup.getExpenses(), createGroup.getAdmin(), createGroup.getUsers());
        groupRepository.createGroup(group);

        return group;
    }
}
