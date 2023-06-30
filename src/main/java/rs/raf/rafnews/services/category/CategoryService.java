package rs.raf.rafnews.services.category;

import rs.raf.rafnews.annotations.Service;
import rs.raf.rafnews.entities.Category;
import rs.raf.rafnews.respositories.category.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

@Service
public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Category getById(Integer id) {
        return categoryRepository.getById(id);
    }

    public Category create(Category category) {
        return categoryRepository.create(category);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Integer id) {
        categoryRepository.delete(id);
    }
}
