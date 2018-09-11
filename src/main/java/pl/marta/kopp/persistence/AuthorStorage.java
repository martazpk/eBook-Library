package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.model.Author;

public interface AuthorStorage {
    void add(Author author);
    boolean isExists(Author author);
    boolean isExists(long id);
    void delete(long id);
    Author get(long id);

}
