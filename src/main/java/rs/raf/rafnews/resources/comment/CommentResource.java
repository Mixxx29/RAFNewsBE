package rs.raf.rafnews.resources.comment;

import rs.raf.rafnews.annotations.Authenticate;
import rs.raf.rafnews.dto.requests.CommentRequest;
import rs.raf.rafnews.entities.Comment;
import rs.raf.rafnews.services.comment.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Authenticate
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticate
    public Comment getById(@PathParam("id") Integer id) {
        return commentService.getById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticate
    public Comment create(CommentRequest commentRequest) {
        return commentService.create(commentRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticate
    public Comment save(Comment comment) {
        return commentService.save(comment);
    }

    @DELETE
    @Path("/{id}")
    @Authenticate
    public void delete(@PathParam("id") Integer id) {
        commentService.delete(id);
    }

    @GET
    @Path("/news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticate
    public List<Comment> getByNews(@PathParam("id") Integer newsId) {
        return commentService.getByNews(newsId);
    }
}
