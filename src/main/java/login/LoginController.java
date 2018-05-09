package login;

import communication.Response;
import domain.User;
import domain.UserStorage;

public class LoginController {
    private final UserStorage userStorage;

    public LoginController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Response login(String login, String password) {
        if(userStorage.isUserExists(login,password)) return Response.aSuccessfulResponse();
        return Response.aFailureResponse("Login or password invalid");
    }
}
