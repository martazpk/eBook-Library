package pl.marta.kopp.domain.author;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.domain.model.Author;
import pl.marta.kopp.domain.service.AuthorService;
import pl.marta.kopp.domain.service.exception.AuthorAlreadyExistsException;
import pl.marta.kopp.persistence.AuthorStorageJpa;

import static org.junit.Assert.*;

public class AuthorServiceTest {
    private AuthorStorageJpa authorStorageJpa;
    private AuthorService service;
    private static final String SOME_NAME = "Adam";
    private static final String SOME_SURNAME = "Mickiewicz";

    @Before
    public void setUp() throws Exception {
        authorStorageJpa=new AuthorStorageJpa();
       service=new AuthorService(authorStorageJpa);

    }

    @Test
    public void shouldAddNewAuthor() throws Exception {
        Author author = new Author(SOME_NAME, SOME_SURNAME);
        long id = service.add(author);
        assertTrue(service.isExists(id));
    }

    @Test(expected = AuthorAlreadyExistsException.class)
    public void shouldThrowExceptionWhenAddTwiceTheSameAuthor() throws Exception {
        Author author = new Author(SOME_NAME, SOME_SURNAME);
        service.add(author);
        service.add(author);
    }

    @Test
    public void shouldGetBookById() throws Exception {

        Author author = new Author(SOME_NAME, SOME_SURNAME);
        service.add(author);
        Author byId = service.get(author.getId());
        assertEquals(SOME_NAME, byId.getName());
        assertEquals(SOME_SURNAME, byId.getSurname());
    }

    @Test
    public void shouldDeleteAuthor() throws Exception {
        Author author = new Author(SOME_NAME, SOME_SURNAME);
        long id = service.add(author);
        service.delete(id);
        assertFalse(service.isExists(id));
    }

}