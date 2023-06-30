package rs.raf.rafnews.services.news;

import rs.raf.rafnews.annotations.Service;
import rs.raf.rafnews.dto.requests.NewsRequest;
import rs.raf.rafnews.dto.responses.PageResponse;
import rs.raf.rafnews.entities.Category;
import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.respositories.category.CategoryRepository;
import rs.raf.rafnews.respositories.news.NewsRepository;
import rs.raf.rafnews.respositories.user.UserRepository;
import rs.raf.rafnews.respositories.util.Pageable;

import javax.inject.Inject;
import java.util.List;

@Service
public class NewsService {
    @Inject
    private NewsRepository newsRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private CategoryRepository categoryRepository;

    public List<News> getAll() {
        return newsRepository.getAll();
    }

    public PageResponse getAll(final int pageIndex, final int pageSize) {
        return PageResponse.builder()
                .items(newsRepository.getAll(Pageable.of(pageIndex, pageSize)))
                .lastPage(newsRepository.pageCount(pageSize))
                .build();
    }

    public News getById(Integer id) {
        return newsRepository.getById(id);
    }

    public News create(NewsRequest newsRequest) {
        User author = userRepository.getById(newsRequest.getAuthorId());
        Category category = categoryRepository.getById(newsRequest.getCategoryId());

        News news = News.builder()
                .title(newsRequest.getTitle())
                .content(newsRequest.getContent())
                .datetime(newsRequest.getDatetime())
                .author(author)
                .category(category)
                .build();
        return newsRepository.create(news);
    }

    public News save(News news) {
        return newsRepository.save(news);
    }

    public void delete(Integer id) {
        newsRepository.delete(id);
    }
}
