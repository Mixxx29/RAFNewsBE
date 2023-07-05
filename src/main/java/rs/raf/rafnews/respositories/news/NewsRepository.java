package rs.raf.rafnews.respositories.news;

import rs.raf.rafnews.annotations.Repository;
import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.respositories.GenericRepository;
import rs.raf.rafnews.respositories.enums.SortDirection;

import java.util.List;

@Repository
public interface NewsRepository extends GenericRepository<News> {
    List<News> getSortedByDate(SortDirection direction);
    List<News> getSortedByVisits(SortDirection direction);
    List<News> getByCategory(int categoryId);
    News getByTitle(String title);
}
