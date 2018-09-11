package pl.marta.kopp.domain.user;

import lombok.Getter;
import pl.marta.kopp.domain.book.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column( unique = true )
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private User() {
    }

    public boolean hasPasswordEqualTo(String password) {
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
