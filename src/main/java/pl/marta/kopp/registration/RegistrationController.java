package pl.marta.kopp.registration;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.User;
import pl.marta.kopp.service.UserStorageDataBase;

public class RegistrationController {
    private static final int MIN_LENGTH = 4;
    private final UserStorageDataBase userStorageDataBase;

    public RegistrationController(UserStorageDataBase userStorageDataBase) {
        this.userStorageDataBase = userStorageDataBase;
    }

    public Response createUser(String login, String password) {
        if (login.length() <= MIN_LENGTH) return Response.aFailureResponse("Login is too short");
        if (password.length() <= MIN_LENGTH) return Response.aFailureResponse("Password is to short");
        if (userStorageDataBase.isUserExists(login)) return Response.aFailureResponse("User already exists");
        userStorageDataBase.addUser(new User(login, password));
        return Response.aSuccessfulResponse();

    }


}
