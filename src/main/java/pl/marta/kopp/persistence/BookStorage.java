package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.Book;

import java.util.List;

public interface BookStorage {
    void add(Book book);
    List getAll();
    Book getById(long id);
    Boolean isExists(long id);
    void delete(long id);
    void update(Book book);


}
