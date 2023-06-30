package rs.raf.rafnews.respositories;

import rs.raf.rafnews.respositories.util.Pageable;

import java.util.List;

public interface GenericRepository<T> {
    List<T> getAll();

    List<T> getAll(Pageable pageable);

    int pageCount(int pageSize);

    T getById(int id);

    T create(T entity);

    T save(T entity);

    void delete(int id);
}
