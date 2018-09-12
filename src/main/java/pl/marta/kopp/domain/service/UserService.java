package pl.marta.kopp.domain.service;

import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.persistence.UserStorage;

public class UserService {

    private final UserStorage storage;

    public UserService(UserStorage storage) {
        this.storage = storage;
    }

    public long add(User user){
        storage.addUser(user);
        return user.getId();
    }
}
