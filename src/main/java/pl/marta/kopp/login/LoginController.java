package pl.marta.kopp.login;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.service.UserStorageDataBase;

public class LoginController {
    private final UserStorageDataBase userStorageDataBase;

    public LoginController(UserStorageDataBase userStorageDataBase) {
        this.userStorageDataBase = userStorageDataBase;
    }

    public Response login(String login, String password) {
        if(userStorageDataBase.isUserExists(login,password)) return Response.aSuccessfulResponse();
        return Response.aFailureResponse("Login or password invalid");
    }
}
