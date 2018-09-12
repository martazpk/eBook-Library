package pl.marta.kopp.domain.service;

import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.domain.service.exception.UserDoesNotExistException;
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

    public User get(long id){
        if(storage.isExists(id)){
           return storage.getById(id);
        } else throw new UserDoesNotExistException(id);
    }
    public boolean isLoginExists(String login){
        return storage.isUserExists(login);
    }

    public boolean isExists(long id){
    return storage.isExists(id);
}

    public boolean isUserExists(String login, String password) {
        return storage.isUserExists(login, password);
    }

    public void update(User user) {
        storage.update(user);
    }

    public void delete(long userId) {
        if(storage.isExists(userId)){
            storage.delete(userId);
        }else throw new UserDoesNotExistException(userId);
    }
}
