package pl.marta.kopp.service;

import pl.marta.kopp.domain.Author;

public interface AuthorStorage {
    void add(Author author);
    boolean isExists(Author author);
    void delete(long id);
    Author get(long id);

}
