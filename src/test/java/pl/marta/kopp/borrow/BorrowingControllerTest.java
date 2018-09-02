package pl.marta.kopp.borrow;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.author.Author;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.domain.boorow.Borrow;
import pl.marta.kopp.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BorrowingControllerTest {
    private final BookStorage bookStorage=new BookStorageJpa();
    private final BorrowStorage borrowStorage=new BorrowStorageJpa();
    private final BorrowingController controller=new BorrowingController(bookStorage,borrowStorage);
    private static final String SOME_TITLE="Harry Potter";
    private static final long SOME_USER_ID=123456L;
    private static final long SOME_BOOK_ID=123456L;


    @Test
    public void shouldNotBorrowBookWhenBookIsNotInStorage() throws Exception {
        Response result=controller.borrowBook(SOME_BOOK_ID,SOME_USER_ID);
        assertFalse(result.getSuccess());
        assertEquals("Sorry, this book is not our catalog.",result.getMessage());
    }

    @Test
    public void shouldNotBorrowBookWhenBookIsAlreadyBorrowed() throws Exception {
        Book book=new Book(SOME_TITLE,null);
        bookStorage.add(book);
        Response result=controller.borrowBook(book.getId(),SOME_USER_ID);
        assertFalse(result.getSuccess());
        assertEquals("Sorry, this book is already borrowed",result.getMessage());
    }

    @Test
    public void shouldBorrowBook() throws Exception {
        Book book=new Book(SOME_TITLE,null);
        bookStorage.add(book);
        Response result=controller.borrowBook(book.getId(),SOME_USER_ID);
        assertTrue(result.getSuccess());
        assertTrue(borrowStorage.isExistsBookId(book.getId()));

    }
}