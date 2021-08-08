package in.shabhushan.lld.expense.controller;

import in.shabhushan.lld.expense.dto.request.CreateGroupDTO;
import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.Group;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.repository.GroupRepository;
import in.shabhushan.lld.expense.repository.UserRepository;
import in.shabhushan.lld.expense.service.GroupService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/expense/user")
public class GroupController {

    private final GroupService service;

    public GroupController(GroupRepository repo) {
        service = new GroupService(repo);
    }

    @POST
    @Path("/")
    public Group createGroup(CreateGroupDTO groupRequest) {
        return service.createGroup(groupRequest);
    }
}
