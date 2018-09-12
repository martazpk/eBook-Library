package pl.marta.kopp.domain.service.exception;

public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException() {
    }

    public UserDoesNotExistException(long id) {
        super("User "+id+" does not exist.");
    }
}
