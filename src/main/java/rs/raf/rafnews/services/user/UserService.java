package rs.raf.rafnews.services.user;

import rs.raf.rafnews.annotations.Service;
import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.respositories.PostgresGenericRepository;
import rs.raf.rafnews.respositories.user.UserRepository;
import rs.raf.rafnews.utils.Security;

import javax.inject.Inject;
import java.util.List;

@Service
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
        user.setPassword(Security.hashPassword(user.getPassword()));
        return userRepository.create(user);
    }

    public User save(User user) {
        user.setPassword(Security.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
