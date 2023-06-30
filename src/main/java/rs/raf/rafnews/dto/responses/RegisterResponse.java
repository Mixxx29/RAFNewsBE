package rs.raf.rafnews.dto.responses;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private String jwt;
    private int statusCode;
    private String message;
}
