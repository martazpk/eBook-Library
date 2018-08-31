package pl.marta.kopp.persistence;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.domain.Author;
import pl.marta.kopp.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookStorageJpaTest {
    private BookStorageJpa bookStorageJpa;
    private AuthorStorage authorStorage;
    private static final String SOME_TITLE = "Wiersze";
    private static final String ANOTHER_TITLE = "Biblia";
    private static final String SOME_AUTHORS_NAME = "Julian";
    private static final String SOME_AUTHORS_SURNAME = "Tuwim";

    @Before
    public void setUp() throws Exception {
        bookStorageJpa = new BookStorageJpa();
    }

    @Test
    public void shouldAddNewBook() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(SOME_AUTHORS_NAME, SOME_AUTHORS_SURNAME));
        Book book = new Book(SOME_TITLE, authors, true);
        bookStorageJpa.add(book);
        assertTrue(bookStorageJpa.isExists(book.getId()));
    }

    @Test
    public void getAll() throws Exception {
        bookStorageJpa.add(new Book(SOME_TITLE,null,false));
        bookStorageJpa.add(new Book(ANOTHER_TITLE,null,false));
        assertEquals(2,bookStorageJpa.getAll().size());
    }

    @Test
    public void shouldGetBookById() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(SOME_AUTHORS_NAME, SOME_AUTHORS_SURNAME));
        Book book = new Book(SOME_TITLE, authors, true);
        bookStorageJpa.add(book);
        Book byId = bookStorageJpa.getById(book.getId());
        assertEquals(SOME_TITLE,byId.getTitle());
    }


    @Test
    public void shouldDeleteBook() throws Exception {
        Book book = new Book(SOME_TITLE, null, true);
        bookStorageJpa.add(book);
        bookStorageJpa.delete(book.getId());
        assertFalse(bookStorageJpa.isExists(book.getId()));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Book book = new Book(SOME_TITLE, null, true);
        bookStorageJpa.add(book);
        Book updatedBook=bookStorageJpa.getById(book.getId());
        updatedBook.setBorrow(false);
        bookStorageJpa.update(updatedBook);
        assertEquals(false,bookStorageJpa.getById(book.getId()).isBorrow());
    }

    @Test
    public void shouldAddAuthorWhenAddBookWithNewAuthor() throws Exception {
        authorStorage=new AuthorStorageJpa();
        List<Author> authors = new ArrayList<>();
        Author author=new Author(SOME_AUTHORS_NAME, SOME_AUTHORS_SURNAME);
        authors.add(author);
        Book book = new Book(SOME_TITLE, authors, true);
        bookStorageJpa.add(book);
        assertTrue(authorStorage.isExists(author));
    }
}