package pl.marta.kopp.domain.author;

import pl.marta.kopp.domain.author.Author;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException(Author author) {
        super(author.getName()+" "+author.getSurname()+" already exists!");
    }
}
