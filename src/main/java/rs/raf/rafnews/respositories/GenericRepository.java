package rs.raf.rafnews.respositories;

import java.util.List;

public interface GenericRepository<T> {
    List<T> getAll();
    T getById(int id);
    T create(T entity);
    T save(T entity);
    void delete(int id);
}
