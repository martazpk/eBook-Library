package pl.marta.kopp.domain.service.exception;

public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException(long id) {
        super("Book "+id+" does not exists");
    }
}
