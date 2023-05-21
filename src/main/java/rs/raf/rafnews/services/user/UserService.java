package rs.raf.rafnews.services.user;

import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.respositories.PostgresAbstractRepository;
import rs.raf.rafnews.respositories.user.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    public User create(User user) {
        return userRepository.create(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}