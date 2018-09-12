package pl.marta.kopp.domain.service;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.domain.model.Author;
import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.domain.model.Borrowing;
import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.domain.service.exception.BookDoesNotExistException;
import pl.marta.kopp.domain.service.exception.UserDoesNotExistException;
import pl.marta.kopp.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

public class BorrowingServiceTest {
    private BorrowingService borrowingService;
    private BorrowStorage borrowingStorage;
    private UserService userService;
    private UserStorage userStorage;
    private BookStorageJpa bookStorage;
    private BookService bookService;
    private static final String USER_NAME="Peter";
    private static final String USER_SURNAME="Parker";
    private static final String TITLE="Java";
    private static final String ISBN="23456789";
    private static final List<Author> AUTHORS=null;
    private static final long INCORRECT_BOOK_ID=123L;
    private static final long INCORRECT_USER_ID=123L;

    @Before
    public void setUp() throws Exception {

        borrowingStorage=new BorrowStorageJpa();
        borrowingService=new BorrowingService(borrowingStorage,userStorage,bookStorage);
        userStorage=new UserStorageJpa();
        userService=new UserService(userStorage);
        bookStorage=new BookStorageJpa();
        bookService=new BookService(bookStorage);
        borrowingService=new BorrowingService(borrowingStorage,userStorage,bookStorage);
    }

    @Test
    public void shouldAddNewBorrowing() throws Exception {
        long userId=givenUser(USER_NAME,USER_SURNAME);
        long bookId=givenBook(ISBN,TITLE,AUTHORS);
        long add = borrowingService.add(bookId, userId);
        assertTrue(borrowingStorage.isExists(add));
        assertTrue(borrowingStorage.isExistsUserId(userId));
        assertTrue(borrowingStorage.isExistsBookId(bookId));

    }

    private long givenBook(String title, String isbn, List<Author> authors) {
        Book book=new Book(isbn,title,authors);
        return bookService.add(book);
    }

    private long givenUser(String userName, String userSurname) {
        User user=new User(userName,userSurname);
        return userService.add(user);
    }

    @Test(expected = BookDoesNotExistException.class)
    public void shouldThrowExceptionWhenBorrowBookWithIncorrectId() throws Exception {
        long userId=givenUser(USER_NAME,USER_SURNAME);
        borrowingService.add(INCORRECT_BOOK_ID,userId);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void shouldThrowExceptionWhenBorrowBookWithIncorrectUserId() throws Exception {
        long bookId=givenBook(ISBN,TITLE,AUTHORS);
        borrowingService.add(bookId,INCORRECT_USER_ID);
    }

    @Test
    public void shouldDeleteBorrowing() throws Exception {
        long userId=givenUser(USER_NAME,USER_SURNAME);
        long bookId=givenBook(ISBN,TITLE,AUTHORS);
        long borrowingId = borrowingService.add(bookId, userId);

        borrowingService.delete(borrowingId);
        assertFalse(borrowingStorage.isExists(borrowingId));
    }

    @Test
    public void shouldReturnBorrowingBookWhenUserIdIsGiven() throws Exception {
        long userId=givenUser(USER_NAME,USER_SURNAME);
        long bookId=givenBook(ISBN,TITLE,AUTHORS);
        borrowingService.add(bookId, userId);
        List<Book> byUserId = borrowingService.getBorrowedBooksByUserId(userId);
        assertEquals(1,byUserId.size());

    }

}