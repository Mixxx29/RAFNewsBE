package rs.raf.rafnews.resources.user;

import rs.raf.rafnews.annotations.Authenticate;
import rs.raf.rafnews.dto.requests.LoginRequest;
import rs.raf.rafnews.dto.requests.RegisterRequest;
import rs.raf.rafnews.dto.responses.LoginResponse;
import rs.raf.rafnews.dto.responses.PageResponse;
import rs.raf.rafnews.dto.responses.RegisterResponse;
import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.services.user.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Authorize(UserType.ADMIN)
    public List<User> getAll() {
        return userService.getAll();
    }

    @GET
    @Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authorize(UserType.ADMIN)
    public PageResponse getAll(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
        return userService.getAll(pageIndex, pageSize);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authorize(UserType.ADMIN)
    public User getById(@PathParam("id") Integer id) {
        return userService.getById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Authorize(UserType.ADMIN)
    public User create(User user) {
        return userService.create(user);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    //@Authorize(UserType.ADMIN)
    public User save(User user) {
        return userService.save(user);
    }

    @DELETE
    @Path("/{id}")
    //@Authorize(UserType.ADMIN)
    public void delete(@PathParam("id") Integer id) {
        userService.delete(id);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse login(LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public RegisterResponse register(RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @GET
    @Path("/verify")
    @Authenticate
    public boolean verify() {
        return true;
    }
}