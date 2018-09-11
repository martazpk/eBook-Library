package pl.marta.kopp.domain.service;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.domain.model.Author;
import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.persistence.AuthorStorage;
import pl.marta.kopp.persistence.AuthorStorageJpa;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BookStorageJpa;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookStorageJpa bookStorage;
    private BookService bookService;
    private AuthorStorageJpa authorStorage;
    private AuthorService authorService;
    private static final String SOME_TITLE = "Wiersze";
    private static final String ANOTHER_TITLE = "Biblia";
    private static final String SOME_AUTHORS_NAME = "Julian";
    private static final String SOME_AUTHORS_SURNAME = "Tuwim";
    private static final String SOME_BOOK_ISBN = "123456789";
    private static final String ANOTHER_BOOK_ISBN = "876655434354";

    @Before
    public void setUp() throws Exception {
        bookStorage = new BookStorageJpa();
        authorStorage = new AuthorStorageJpa();
        bookService = new BookService(bookStorage);
        authorService = new AuthorService(authorStorage);
    }

    @Test
    public void shouldAddNewBook() throws Exception {
        long identifier = givenBook(SOME_BOOK_ISBN, SOME_TITLE, null);

        assertTrue(bookStorage.isExists(identifier));
    }

    @Test
    public void getAll() throws Exception {
        givenBook(SOME_BOOK_ISBN, SOME_TITLE, null);
        givenBook(ANOTHER_BOOK_ISBN, ANOTHER_TITLE, null);
        assertEquals(2, bookStorage.getAll().size());
    }

    @Test
    public void shouldGetBookById() throws Exception {
        long identifier = givenBook(SOME_BOOK_ISBN,SOME_TITLE,null);
        Book byId = bookService.getById(identifier);
        assertEquals(SOME_TITLE, byId.getTitle());

    }


    @Test
    public void shouldDeleteBook() throws Exception {
       long identifier=givenBook(SOME_BOOK_ISBN,SOME_TITLE,null);
        bookService.delete(identifier);
        assertFalse(bookStorage.isExists(identifier));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        long identifier=givenBook(SOME_BOOK_ISBN,SOME_TITLE,null);
        Book updatedBook = bookService.getById(identifier);
        updatedBook.setBorrow(false);
        bookService.update(updatedBook);
        Boolean result=bookService.getById(identifier).isBorrow();
        assertEquals(false, result);
    }

    @Test
    public void shouldAddAuthorWhenAddBookWithNewAuthor() throws Exception {
        List<Author> authors = new ArrayList<>();
        Author author = new Author(SOME_AUTHORS_NAME, SOME_AUTHORS_SURNAME);
        authors.add(author);
       givenBook(SOME_BOOK_ISBN,SOME_TITLE,authors);
        assertTrue(authorStorage.isExists(author));
    }

    private long givenBook(String isbn, String title, List<Author> authors) {
        Book book = new Book(isbn, title, authors);
        return bookService.add(book);
    }
}