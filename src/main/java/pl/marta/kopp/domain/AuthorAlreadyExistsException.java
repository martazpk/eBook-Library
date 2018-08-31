package pl.marta.kopp.domain;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException(Author author) {
        super(author.getName()+" "+author.getSurname()+" already exists!");
    }
}
