package in.shabhushan.lld.expense.controller;

import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.repository.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/expense/user")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @POST
    @Path("/")
    public boolean createUser(User user) {
        return this.repo.createUser(user);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int userId) {
        return this.repo.getUserByID(userId);
    }
}
