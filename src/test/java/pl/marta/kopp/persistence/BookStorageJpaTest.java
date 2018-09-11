package pl.marta.kopp.persistence;

import org.junit.Before;
import org.junit.Test;
import pl.marta.kopp.domain.model.Author;
import pl.marta.kopp.domain.model.Book;

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
        authorStorage=new AuthorStorageJpa();
    }


}