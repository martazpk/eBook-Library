package pl.marta.kopp.persistence;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.domain.author.Author;
import pl.marta.kopp.domain.author.AuthorAlreadyExistsException;

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
        assertEquals(SOME_NAME, byId.getName());
        assertEquals(SOME_SURNAME, byId.getSurname());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        Author author = new Author(SOME_NAME, SOME_SURNAME);
        authorStorageJpa.add(author);
        authorStorageJpa.delete(author.getId());
        assertFalse(authorStorageJpa.isExists(author));
    }

}