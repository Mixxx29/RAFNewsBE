package rs.raf.rafnews.dto.requests;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginRequest {
    private String email;
    private String password;
}
