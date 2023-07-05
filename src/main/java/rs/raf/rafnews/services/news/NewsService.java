package rs.raf.rafnews.services.news;

import rs.raf.rafnews.annotations.Service;
import rs.raf.rafnews.dto.requests.NewsRequest;
import rs.raf.rafnews.dto.responses.PageResponse;
import rs.raf.rafnews.entities.Category;
import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.respositories.category.CategoryRepository;
import rs.raf.rafnews.respositories.enums.SortDirection;
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

    public List<News> getSortedByDate(SortDirection direction) {
        return newsRepository.getSortedByDate(direction);
    }

    public List<News> getSortedByVisits(SortDirection direction) {
        return newsRepository.getSortedByVisits(direction);
    }

    public List<News> getByCategory(String name) {
        final Category category = categoryRepository.getByName(name);
        System.out.println(category);
        return newsRepository.getByCategory(category.getId());
    }

    public News getByTitle(String title) {
        return newsRepository.getByTitle(title);
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

    public News save(NewsRequest newsRequest) {
        News savedNews = newsRepository.getById(newsRequest.getId());
        Category category = categoryRepository.getById(newsRequest.getCategoryId());

        News news = News.builder()
                .id(savedNews.getId())
                .title(newsRequest.getTitle())
                .content(newsRequest.getContent())
                .datetime(savedNews.getDatetime())
                .visits(savedNews.getVisits())
                .author(savedNews.getAuthor())
                .category(category)
                .build();
        return newsRepository.save(news);
    }

    public void delete(Integer id) {
        newsRepository.delete(id);
    }
}
