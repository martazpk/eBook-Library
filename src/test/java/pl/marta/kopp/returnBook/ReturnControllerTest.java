package pl.marta.kopp.returnBook;

import org.junit.Test;
import pl.marta.kopp.borrow.BorrowingController;
import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BookStorageJpa;
import pl.marta.kopp.persistence.BorrowStorage;
import pl.marta.kopp.persistence.BorrowStorageJpa;

import static org.junit.Assert.*;

public class ReturnControllerTest {
    private final BookStorage bookStorage = new BookStorageJpa();
    private final BorrowStorage borrowStorage = new BorrowStorageJpa();
    private final BorrowingController borrowingController = new BorrowingController(bookStorage, borrowStorage);
    private final ReturnController controller = new ReturnController(bookStorage, borrowStorage);
    private static final String SOME_TITLE = "Harry Potter";
    private static final long SOME_USER_ID = 123456L;
    private static final long INCORRECT_BOOK_ID = 123456L;


    @Test
    public void shouldNotReturnBookWhenUserDoesNotHaveAnyBorrowedBook() throws Exception {
        Book book = new Book(SOME_TITLE, null);
        bookStorage.add(book);
        borrowingController.borrowBook(book.getId(), SOME_USER_ID);
        Response result = controller.returnBook(INCORRECT_BOOK_ID, SOME_USER_ID);
        assertFalse(result.getSuccess());
        assertEquals("You don't have any borrowed book.", result.getMessage());
    }

    @Test
    public void shouldNotReturnBookWhenIncorrectBookIdIsGiven() throws Exception {
        Response result = controller.returnBook(INCORRECT_BOOK_ID, SOME_USER_ID);
        assertFalse(result.getSuccess());
        assertEquals("Invalid Book Id", result.getMessage());
    }

    @Test
    public void shouldReturnBook() throws Exception {
        Book book = new Book(SOME_TITLE, null);
        bookStorage.add(book);
        borrowingController.borrowBook(book.getId(), SOME_USER_ID);
        Response result = controller.returnBook(book.getId(), SOME_USER_ID);
        assertTrue(result.getSuccess());
        assertFalse(borrowStorage.isExistsBookId(book.getId()));
    }
}