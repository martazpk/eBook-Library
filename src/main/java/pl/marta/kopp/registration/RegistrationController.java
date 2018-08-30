package pl.marta.kopp.registration;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.User;
import pl.marta.kopp.service.UserStorageJpa;

public class RegistrationController {
    private static final int MIN_LENGTH = 4;
    private final UserStorageJpa userStorageJpa;

    public RegistrationController(UserStorageJpa userStorageJpa) {
        this.userStorageJpa = userStorageJpa;
    }

    public Response createUser(String login, String password) {
        if (login.length() <= MIN_LENGTH) return Response.aFailureResponse("Login is too short");
        if (password.length() <= MIN_LENGTH) return Response.aFailureResponse("Password is to short");
        if (userStorageJpa.isUserExists(login)) return Response.aFailureResponse("User already exists");
        userStorageJpa.addUser(new User(login, password));
        return Response.aSuccessfulResponse();

    }


}
