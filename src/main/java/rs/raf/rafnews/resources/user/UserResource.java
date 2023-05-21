package rs.raf.rafnews.resources.user;

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
    public List<User> getAll() {
        return userService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") Integer id) {
        return userService.getById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User user) {
        return userService.create(user);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public User save(User user) {
        return userService.save(user);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        userService.delete(id);
    }
}