package pl.marta.kopp.registration;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.domain.service.UserService;

public class RegistrationController {
    private static final int MIN_LENGTH = 4;
    private final UserService service;

    public RegistrationController(UserService service) {
        this.service = service;
    }


    public Response createUser(String login, String password) {
        if (login.length() <= MIN_LENGTH) return Response.aFailureResponse("Login is too short");
        if (password.length() <= MIN_LENGTH) return Response.aFailureResponse("Password is to short");
        if (service.isLoginExists(login)) return Response.aFailureResponse("User already exists");
        service.add(new User(login, password));
        return Response.aSuccessfulResponse();
    }
}
