package pl.marta.kopp.domain;

import javax.persistence.*;

@Entity
@Table
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

    public User() {
    }

    public boolean hasPasswordEqualTo(String password) {
        return this.password.equals(password);
    }


}
