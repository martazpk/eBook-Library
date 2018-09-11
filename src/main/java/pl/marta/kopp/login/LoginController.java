package pl.marta.kopp.login;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.persistence.UserStorage;

public class LoginController {
    private final UserStorage userStorage;

    public LoginController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Response login(String login, String password) {
        if(userStorage.isUserExists(login,password)) return Response.aSuccessfulResponse();
        return Response.aFailureResponse("Login or password invalid");
    }
    public User getUser(String login,String password){
        if(userStorage.isUserExists(login,password))
        return userStorage.getByLoginAndPassword(login,password);
        else throw new UserDoesNotExistException();
    }
}
