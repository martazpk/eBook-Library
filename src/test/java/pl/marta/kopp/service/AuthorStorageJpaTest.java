package pl.marta.kopp.service;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.domain.Author;
import pl.marta.kopp.domain.AuthorAlreadyExistsException;
import pl.marta.kopp.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorStorageJpaTest {
    private AuthorStorageJpa authorStorageJpa;
    private static final String SOME_NAME = "Adam";
    private static final String SOME_SURNAME = "Mickiewicz";

    @Before
    public void setUp() throws Exception {
        authorStorageJpa = new AuthorStorageJpa();

    }

    @Test
    public void shouldAddNewAuthor() throws Exception {
        Author author = new Author(SOME_NAME, SOME_SURNAME);
        authorStorageJpa.add(author);
        assertTrue(authorStorageJpa.isExists(author));
    }

    @Test(expected = AuthorAlreadyExistsException.class)
    public void shouldThrowExceptionWhenAddTwiceTheSameAuthor() throws Exception {
        Author author = new Author(SOME_NAME, SOME_SURNAME);
        authorStorageJpa.add(author);
        authorStorageJpa.add(author);
    }

    @Test
    public void shouldGetBookById() throws Exception {

        Author author = new Author(SOME_NAME, SOME_SURNAME);
        authorStorageJpa.add(author);
        Author byId = authorStorageJpa.get(author.getId());
        assertEquals(SOME_NAME, byId.getAuthorName());
        assertEquals(SOME_SURNAME, byId.getAuthorSurname());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        Author author = new Author(SOME_NAME, SOME_SURNAME);
        authorStorageJpa.add(author);
        authorStorageJpa.delete(author.getId());
        assertFalse(authorStorageJpa.isExists(author));
    }

}