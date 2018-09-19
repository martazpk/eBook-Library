package pl.marta.kopp.domain.service;

import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.domain.model.Borrowing;
import pl.marta.kopp.domain.service.exception.BookDoesNotExistException;
import pl.marta.kopp.library.BorrowDoesNotExistException;
import pl.marta.kopp.domain.service.exception.UserDoesNotExistException;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BorrowStorage;
import pl.marta.kopp.persistence.UserStorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BorrowingService {
    private final BorrowStorage storage;
    private final UserStorage userStorage;
    private final BookStorage bookStorage;

    public BorrowingService(BorrowStorage storage, UserStorage userStorage, BookStorage bookStorage) {
        this.storage = storage;
        this.userStorage = userStorage;
        this.bookStorage = bookStorage;
    }

    public long add(long bookId, long userId) {
        if (!userStorage.isExists(userId)) throw new UserDoesNotExistException();
        if (!bookStorage.isExists(bookId)) throw new BookDoesNotExistException(bookId);
        Borrowing borrowing = new Borrowing.Builder().bookId(bookId).userId(userId).build();
        storage.add(borrowing);
        return borrowing.getId();
    }

    public void delete(long id) {
        if (storage.isExists(id)) {
            storage.delete(id);
        } else throw new BorrowDoesNotExistException(id);
    }

    public Borrowing getByBookId(long bookId) {
        if (storage.isExistsBookId(bookId)) {
            return storage.getByBookId(bookId);
        } else throw new BookDoesNotExistException(bookId);

    }


    private List<Borrowing> getBorrowingByUserId(long userId) {
            return storage.getListByUserId(userId);

    }

    public List<Book> getBorrowedBooksByUserId(long id) {
        List<Borrowing> borrowings = getBorrowingByUserId(id);
        List<Book> books = new ArrayList<>();
        for (Borrowing b : borrowings) {
            long bookId = b.getBookId();
            Book book = bookStorage.getById(bookId);
            books.add(book);
        }
        return books;

    }

    public boolean isExists(long bookId) {
        return storage.isExistsBookId(bookId);
    }
}