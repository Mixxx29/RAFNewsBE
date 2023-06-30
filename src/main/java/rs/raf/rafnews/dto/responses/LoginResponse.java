package rs.raf.rafnews.dto.responses;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String jwt;
    private int statusCode;
    private String message;
}
