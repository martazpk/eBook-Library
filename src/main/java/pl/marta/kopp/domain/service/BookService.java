package pl.marta.kopp.domain.service;

import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.domain.service.exception.BookAlreadyExistException;
import pl.marta.kopp.library.BookDoesNotExistException;
import pl.marta.kopp.persistence.BookStorageJpa;

public class BookService {
    private final BookStorageJpa storage;

    public BookService(BookStorageJpa storageJpa) {
        this.storage = storageJpa;
    }

    public long add(Book book) {
        if (storage.isExists(book.getIsbn())) {
            throw new BookAlreadyExistException(book.getIsbn());
        } else storage.add(book);
        return book.getId();
    }

    public void delete(long id) {
        if (storage.isExists(id)) {
            storage.delete(id);
        } else throw new BookDoesNotExistException(id);
    }

    public Book get(long id) {
        if (storage.isExists(id)) {
            return storage.getById(id);
        } else throw new BookDoesNotExistException(id);
    }

    public void setBorrow(long id, boolean condition) {
        if (storage.isExists(id)) {
            storage.setBorrow(id, condition);
        } else throw new BookDoesNotExistException(id);
    }

    public void update(Book book) {
        if (storage.isExists(book.getId())) {
            storage.update(book);
        } else throw new BookDoesNotExistException(book.getId());
    }

    public Book getById(long id){
        if(storage.isExists(id)){
            return storage.getById(id);
        }else throw new BookDoesNotExistException(id);
    }

}
