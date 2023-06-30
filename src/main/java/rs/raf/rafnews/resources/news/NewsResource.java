package rs.raf.rafnews.resources.news;

import rs.raf.rafnews.dto.requests.NewsRequest;
import rs.raf.rafnews.dto.responses.PageResponse;
import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.services.news.NewsService;

import javax.inject.Inject;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public News create(NewsRequest newsRequest) {
        return newsService.create(newsRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public News save(News news) {
        return newsService.save(news);
    }

    @DELETE
    @Path("/{id}")
    //@Authenticate
    public void delete(@PathParam("id") Integer id) {
        newsService.delete(id);
    }
}
