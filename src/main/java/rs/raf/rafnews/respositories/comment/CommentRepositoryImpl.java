package rs.raf.rafnews.respositories.comment;

import rs.raf.rafnews.entities.Comment;
import rs.raf.rafnews.respositories.PostgresGenericRepository;

public class CommentRepositoryImpl extends PostgresGenericRepository<Comment> implements CommentRepository {
    public CommentRepositoryImpl() {
        super(Comment.class);
    }
}
