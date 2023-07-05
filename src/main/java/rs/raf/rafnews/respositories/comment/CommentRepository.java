package rs.raf.rafnews.respositories.comment;

import rs.raf.rafnews.annotations.Repository;
import rs.raf.rafnews.entities.Comment;
import rs.raf.rafnews.respositories.GenericRepository;

import java.util.List;

@Repository
public interface CommentRepository extends GenericRepository<Comment> {
    List<Comment> getByNews(int newsId);
}
