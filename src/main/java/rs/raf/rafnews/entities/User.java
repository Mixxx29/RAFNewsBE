package rs.raf.rafnews.entities;

import rs.raf.rafnews.entities.utils.UserStatus;
import rs.raf.rafnews.entities.utils.UserType;

import java.util.Objects;

public class User {

    private Integer id;
    private String email;
    private String name;
    private String username;
    private String surname;
    private UserType type;
    private UserStatus status;
    private String password;

    public User() {
    }

    public User(
            Integer id,
            String email,
            String name,
            String username,
            String surname,
            UserType type,
            UserStatus status,
            String password
    ) {
        this();
        this.id = id;
        this.email = email;
        this.name = name;
        this.username = username;
        this.surname = surname;
        this.type = type;
        this.status = status;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", password='" + password + '\'' +
                '}';
    }
}
