package rs.raf.rafnews.dto.requests;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterRequest {
    private String email;
    private String name;
    private String surname;
    private String password;
}
