package pl.marta.kopp.service;

import pl.marta.kopp.domain.User;

public interface UserStorage {
    boolean isUserExists(String login);
    boolean isUserExists(String login, String password);
    void addUser(User user);
}
