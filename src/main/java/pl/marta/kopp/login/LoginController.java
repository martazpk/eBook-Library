package pl.marta.kopp.login;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.service.UserStorageImpl;

public class LoginController {
    private final UserStorageImpl userStorageImpl;

    public LoginController(UserStorageImpl userStorageImpl) {
        this.userStorageImpl = userStorageImpl;
    }

    public Response login(String login, String password) {
        if(userStorageImpl.isUserExists(login,password)) return Response.aSuccessfulResponse();
        return Response.aFailureResponse("Login or password invalid");
    }
}
