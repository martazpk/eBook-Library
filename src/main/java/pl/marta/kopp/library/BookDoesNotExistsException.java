package pl.marta.kopp.library;

public class BookDoesNotExistsException extends RuntimeException {
    public BookDoesNotExistsException(long id) {
        super("Book "+id+" does not exists");
    }
}
