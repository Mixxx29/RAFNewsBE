package rs.raf.rafnews.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.raf.rafnews.annotations.Entity;
import rs.raf.rafnews.annotations.ID;
import rs.raf.rafnews.entities.enums.UserStatus;
import rs.raf.rafnews.entities.enums.UserType;

import javax.validation.constraints.NotNull;

@Entity("users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ID
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private UserType type;
    private UserStatus status;
    private String password;
}
