package pl.marta.kopp.configuration;

import pl.marta.kopp.domain.model.Author;
import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.persistence.BookStorage;

import java.util.ArrayList;
import java.util.List;

public class InitConstraints {

    public static void create(BookStorage bookStorage) {

        Author author = new Author("Robert", "Martin");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        Book book = new Book("Clean Code", authors);
        bookStorage.add(book);

        Author author1 = new Author("Kent", "Beck");
        List<Author> authors1 = new ArrayList<>();
        authors1.add(author1);
        Book book1 = new Book("Test Driven Development: By Example", authors1);
        bookStorage.add(book1);

        Author author2 = new Author("Eric", "Freeman");
        Author author22 = new Author(" Elisabeth", "Freeman");
        List<Author> authors2 = new ArrayList<>();
        authors2.add(author2);
        authors2.add(author22);
        Book book2 = new Book("Head First Design Patterns", authors2);
        bookStorage.add(book2);

        Author author3 = new Author("Kathy", "Sierra");
        List<Author> authors3 = new ArrayList<>();
        authors3.add(author3);
        Book book3 = new Book("Head First Java", authors3);
        bookStorage.add(book3);

        Author author4 = new Author("Craig", "Walls");
        List<Author> authors4 = new ArrayList<>();
        authors4.add(author4);
        Book book4 = new Book("Spring in Action", authors4);
        bookStorage.add(book4);

        Author author5 = new Author("Raoul Gabriel", "Urma");
        List<Author> authors5 = new ArrayList<>();
        authors5.add(author5);
        Book book5 = new Book("Java 8 in Action", authors5);
        bookStorage.add(book5);

    }}
