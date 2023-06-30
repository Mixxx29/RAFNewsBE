package rs.raf.rafnews.respositories.category;

import rs.raf.rafnews.entities.Category;
import rs.raf.rafnews.respositories.PostgresGenericRepository;

public class CategoryRepositoryImpl extends PostgresGenericRepository<Category> implements CategoryRepository {
    public CategoryRepositoryImpl() {
        super(Category.class);
    }
}
