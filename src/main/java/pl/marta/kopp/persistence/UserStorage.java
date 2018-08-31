package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.User;

public interface UserStorage {
    boolean isUserExists(String login);
    boolean isUserExists(String login, String password);
    void addUser(User user);
    void delete (long id);
    void update(User user);
    User getById(long id);
}
