package rs.raf.rafnews.resources.news;

import rs.raf.rafnews.dto.requests.NewsRequest;
import rs.raf.rafnews.dto.responses.PageResponse;
import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.respositories.enums.SortDirection;
import rs.raf.rafnews.services.news.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsResource {
    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public List<News> getAll() {
        return newsService.getAll();
    }

    @GET
    @Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authorize(UserType.ADMIN)
    public PageResponse getAll(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
        return newsService.getAll(pageIndex, pageSize);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public News getById(@PathParam("id") Integer id) {
        return newsService.getById(id);
    }

    @GET
    @Path("/latest")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public List<News> getSortedByDate(@QueryParam("direction") SortDirection direction) {
        if (direction == null) {
            direction = SortDirection.ASC;
        }
        return newsService.getSortedByDate(direction);
    }

    @GET
    @Path("/popular")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public List<News> getSortedByVisits(@QueryParam("direction") SortDirection direction) {
        if (direction == null) {
            direction = SortDirection.ASC;
        }
        return newsService.getSortedByVisits(direction);
    }

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public List<News> getByCategory(@QueryParam("name") String name) {
        return newsService.getByCategory(name);
    }

    @GET
    @Path("/title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public News getByTitle(@PathParam("title") String title) {
        return newsService.getByTitle(title);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public News create(@Valid NewsRequest newsRequest) {
        return newsService.create(newsRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public News save(@Valid NewsRequest newsRequest) {
        return newsService.save(newsRequest);
    }

    @DELETE
    @Path("/{id}")
    //@Authenticate
    public void delete(@PathParam("id") Integer id) {
        newsService.delete(id);
    }
}
