package rs.raf.rafnews.respositories.news;

import rs.raf.rafnews.annotations.Repository;
import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.respositories.GenericRepository;

@Repository
public interface NewsRepository extends GenericRepository<News> {

}
