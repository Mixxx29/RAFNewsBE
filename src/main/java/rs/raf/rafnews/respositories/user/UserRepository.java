package rs.raf.rafnews.respositories.user;

import rs.raf.rafnews.entities.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User getById(Integer id);
    User create(User user);
    User save(User user);
    void delete(Integer id);
}
