package rs.raf.rafnews.respositories.user;

import rs.raf.rafnews.respositories.PostgresGenericRepository;
import rs.raf.rafnews.entities.User;

public class UserPostgresRepository extends PostgresGenericRepository<User> implements UserRepository {
    public UserPostgresRepository() {
        super(User.class);
    }
}
