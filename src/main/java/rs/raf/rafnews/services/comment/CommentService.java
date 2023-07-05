package rs.raf.rafnews.services.comment;

import rs.raf.rafnews.annotations.Service;
import rs.raf.rafnews.dto.requests.CommentRequest;
import rs.raf.rafnews.entities.Comment;
import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.respositories.comment.CommentRepository;
import rs.raf.rafnews.respositories.news.NewsRepository;
import rs.raf.rafnews.respositories.user.UserRepository;

import javax.inject.Inject;
import java.util.List;

@Service
public class CommentService {

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private NewsRepository newsRepository;

    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    public Comment getById(Integer id) {
        return commentRepository.getById(id);
    }

    public Comment create(CommentRequest commentRequest) {
        User author = userRepository.getById(commentRequest.getAuthorId());
        News news = newsRepository.getById(commentRequest.getNewsId());
        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .datetime(commentRequest.getDatetime())
                .author(author)
                .news(news)
                .build();
        return commentRepository.create(comment);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Integer id) {
        commentRepository.delete(id);
    }

    public List<Comment> getByNews(int newsId) {
        return commentRepository.getByNews(newsId);
    }
}
