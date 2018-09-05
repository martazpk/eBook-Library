package pl.marta.kopp.registration;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.user.User;
import pl.marta.kopp.persistence.UserStorage;
import pl.marta.kopp.persistence.UserStorageJpa;

public class RegistrationController {
    private static final int MIN_LENGTH = 4;
    private final UserStorage userStorage;

    public RegistrationController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Response createUser(String login, String password) {
        if (login.length() <= MIN_LENGTH) return Response.aFailureResponse("Login is too short");
        if (password.length() <= MIN_LENGTH) return Response.aFailureResponse("Password is to short");
        if (userStorage.isUserExists(login)) return Response.aFailureResponse("User already exists");
        userStorage.addUser(new User(login, password));
        return Response.aSuccessfulResponse();

    }
}
