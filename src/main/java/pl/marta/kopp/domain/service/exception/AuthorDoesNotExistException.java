package pl.marta.kopp.domain.service.exception;

public class AuthorDoesNotExistException extends RuntimeException {
    public AuthorDoesNotExistException(long id) {
        super("Author "+id+" does not exist");
    }
}
