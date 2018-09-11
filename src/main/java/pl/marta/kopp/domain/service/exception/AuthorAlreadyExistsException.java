package pl.marta.kopp.domain.service.exception;

import pl.marta.kopp.domain.model.Author;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException(Author author) {
        super(author.getName()+" "+author.getSurname()+" already exists!");
    }
}
