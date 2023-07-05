package rs.raf.rafnews.services.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import rs.raf.rafnews.annotations.Service;
import rs.raf.rafnews.dto.requests.LoginRequest;
import rs.raf.rafnews.dto.requests.RegisterRequest;
import rs.raf.rafnews.dto.responses.LoginResponse;
import rs.raf.rafnews.dto.responses.PageResponse;
import rs.raf.rafnews.dto.responses.RegisterResponse;
import rs.raf.rafnews.entities.User;
import rs.raf.rafnews.entities.enums.UserStatus;
import rs.raf.rafnews.entities.enums.UserType;
import rs.raf.rafnews.respositories.user.UserRepository;
import rs.raf.rafnews.respositories.util.Pageable;
import rs.raf.rafnews.utils.Security;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {

    private final String SECRET_KEY = "SECRET_KEY";

    @Inject
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public PageResponse getAll(final int pageIndex, final int pageSize) {
        return PageResponse.builder()
                .items(userRepository.getAll(Pageable.of(pageIndex, pageSize)))
                .lastPage(userRepository.pageCount(pageSize))
                .build();
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

    public LoginResponse login(LoginRequest loginRequest) {
        int statusCode = 200;
        String jwt = null;
        String message = "";

        User user = userRepository.getByEmail(loginRequest.getEmail());
        if (Security.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            jwt = JWT.create()
                    .withClaim("id", user.getId())
                    .withClaim("name", user.getName() + " " + user.getSurname())
                    .withClaim("type", user.getType().toString())
                    .sign(algorithm);
        } else {
            statusCode = 401;
            message = "Invalid e-mail or password!";
        }
        return LoginResponse.builder()
                .statusCode(statusCode)
                .jwt(jwt)
                .message(message)
                .build();
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        try {
            userRepository.getByEmail(registerRequest.getEmail());
        } catch (Exception e) {
            return RegisterResponse.builder()
                    .statusCode(400)
                    .message("E-mail is already used!")
                    .build();
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .type(UserType.CONTENT_CREATOR)
                .status(UserStatus.ACTIVE)
                .password(Security.hashPassword(registerRequest.getPassword()))
                .build();

        user = userRepository.create(user);

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String jwt = JWT.create()
                .withClaim("id", user.getId())
                .withClaim("name", user.getName() + " " + user.getSurname())
                .withClaim("type", user.getType().toString())
                .sign(algorithm);

        return RegisterResponse.builder()
                .statusCode(200)
                .jwt(jwt)
                .message("OK")
                .build();
    }

    public boolean isAuthenticated(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    public boolean isAuthorized(String token, UserType level) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt;
        UserType userType;
        try {
            jwt = verifier.verify(token);
            userType = UserType.valueOf(jwt.getClaim("type").asString());
        } catch (JWTVerificationException | IllegalArgumentException e) {
            return false;
        }

        return userType.ordinal() <= level.ordinal();
    }
}
