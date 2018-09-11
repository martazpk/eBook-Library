package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.model.Book;

import java.util.List;

public interface BookStorage {
    void add(Book book);
    List getAll();
    List getPresentBooks();
    Book getById(long id);
    Boolean isExists(long id);
    Boolean isExists(String isbn);
    void delete(long id);
    void update(Book book);
    void setBorrow(long id,boolean condition);
}
