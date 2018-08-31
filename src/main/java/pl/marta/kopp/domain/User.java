package pl.marta.kopp.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column( unique = true )
    private String login;
    @Column
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
