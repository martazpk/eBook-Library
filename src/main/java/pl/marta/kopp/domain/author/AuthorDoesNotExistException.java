package pl.marta.kopp.domain.author;

public class AuthorDoesNotExistException extends RuntimeException {
    public AuthorDoesNotExistException(long id) {
        super("Author "+id+" does not exist");
    }
}
