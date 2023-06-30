package rs.raf.rafnews.respositories.news;

import rs.raf.rafnews.entities.News;
import rs.raf.rafnews.respositories.PostgresGenericRepository;

public class NewsRepositoryImpl extends PostgresGenericRepository<News> implements NewsRepository {
    public NewsRepositoryImpl() {
        super(News.class);
    }
}
