package pl.marta.kopp.login;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.service.UserStorageJpa;

public class LoginController {
    private final UserStorageJpa userStorageJpa;

    public LoginController(UserStorageJpa userStorageJpa) {
        this.userStorageJpa = userStorageJpa;
    }

    public Response login(String login, String password) {
        if(userStorageJpa.isUserExists(login,password)) return Response.aSuccessfulResponse();
        return Response.aFailureResponse("Login or password invalid");
    }
}
