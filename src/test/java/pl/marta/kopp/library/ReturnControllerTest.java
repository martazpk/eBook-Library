package pl.marta.kopp.library;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.model.Author;
import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.domain.service.BookService;
import pl.marta.kopp.domain.service.BorrowingService;
import pl.marta.kopp.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

public class ReturnControllerTest {
    private  UserStorage userStorage;
    private  BookStorageJpa bookStorage;
    private  BookService bookService;
    private  BorrowStorage borrowStorage;

    private ReturnController controller;

    private static final String SOME_TITLE = "Harry Potter";
    private static final String SOME_ISBN = "9876543456";
    private static final List<Author> AUTHORS = null;
    private static final String SOME_LOGIN = "marta";
    private static final String SOME_PASSWORD = "kopp";
    private static final long INCORRECT_USER_ID = 123456L;
    private static final long INCORRECT_BOOK_ID = 123456L;
    private long bookId;
    private long userId;

    @Before
    public void setUp() throws Exception {
        userStorage = new UserStorageJpa();
        bookStorage = new BookStorageJpa();
        bookService = new BookService(bookStorage);
        borrowStorage = new BorrowStorageJpa();
        BorrowingService borrowingService = new BorrowingService(borrowStorage, userStorage, bookStorage);
        BorrowingController borrowingController = new BorrowingController(bookService, borrowingService);
        controller = new ReturnController(bookService, borrowingService);

        bookId = givenBook(SOME_ISBN, SOME_TITLE, AUTHORS);
        userId = givenUser(SOME_LOGIN, SOME_PASSWORD);
        borrowingController.borrowBook(bookId, userId);
    }

    @Test
    public void shouldNotReturnBookWhenIncorrectBookIdIsGiven() throws Exception {
        Response result = controller.returnBook(userId, INCORRECT_BOOK_ID);

        assertFalse(result.getSuccess());
        assertEquals("Invalid Book Id", result.getMessage());
    }

    @Test
    public void shouldNotReturnWhenIncorrectUserIsGiven() throws Exception {
        Response result = controller.returnBook(INCORRECT_USER_ID, bookId);

        assertFalse(result.getSuccess());
        assertEquals("Invalid user id", result.getMessage());

    }

    @Test
    public void shouldReturnBook() throws Exception {
        Response result = controller.returnBook(userId, bookId);

        assertTrue(result.getSuccess());
        assertFalse(borrowStorage.isExistsBookId(bookId));
        boolean isBorrow = bookStorage.getById(bookId).isBorrow();
        assertEquals(false, isBorrow);
    }

    private long givenUser(String name, String surname) {
        User user = new User(name, surname);
        userStorage.addUser(user);
        return user.getId();
    }

    private long givenBook(String isbn, String title, List<Author> authors) {
        Book book = new Book(isbn, title, authors);
        return bookService.add(book);
    }
}
