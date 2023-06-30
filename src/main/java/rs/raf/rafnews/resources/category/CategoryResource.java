package rs.raf.rafnews.resources.category;

import rs.raf.rafnews.annotations.Authenticate;
import rs.raf.rafnews.entities.Category;
import rs.raf.rafnews.services.category.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticate
    public Category getById(@PathParam("id") Integer id) {
        return categoryService.getById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticate
    public Category create(Category category) {
        return categoryService.create(category);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticate
    public Category save(Category category) {
        return categoryService.save(category);
    }

    @DELETE
    @Path("/{id}")
    @Authenticate
    public void delete(@PathParam("id") Integer id) {
        categoryService.delete(id);
    }
}