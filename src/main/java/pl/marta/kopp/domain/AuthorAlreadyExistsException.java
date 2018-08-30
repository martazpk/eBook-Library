package pl.marta.kopp.domain;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException(Author author) {
        super(author.getAuthorName()+" "+author.getAuthorSurname()+" already exists!");
    }
}
