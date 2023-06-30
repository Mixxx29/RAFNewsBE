package rs.raf.rafnews.respositories.user;

import rs.raf.rafnews.annotations.Repository;
import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.respositories.GenericRepository;

@Repository
public interface UserRepository extends GenericRepository<User> {
    User getByEmail(String email);
}
