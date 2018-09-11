package pl.marta.kopp.library;

public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException(long id) {
        super("Book "+id+" does not exists");
    }
}
