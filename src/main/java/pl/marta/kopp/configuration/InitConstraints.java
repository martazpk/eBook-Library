package pl.marta.kopp.configuration;

import pl.marta.kopp.domain.model.Author;
import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.persistence.BookStorage;

import java.util.ArrayList;
import java.util.List;

public class InitConstraints {

    public static void create(BookStorage bookStorage) {

        List<Author> authors = createListOfAuthors(new Author("Robert", "Martin"));
        Book book = new Book("12345", "Clean Code", authors);
        bookStorage.add(book);

        List<Author> authors1 =createListOfAuthors(new Author("Kent", "Beck"));
        Book book1 = new Book("34567899987", "Test Driven Development: By Example", authors1);
        bookStorage.add(book1);


        List<Author> authors2 = createListOfAuthors(new Author("Eric", "Freeman"),
                new Author(" Elisabeth", "Freeman"));
        Book book2 = new Book("76543456", "Head First Design Patterns", authors2);
        bookStorage.add(book2);


        List<Author> authors3 = createListOfAuthors(new Author("Kathy", "Sierra"));
        Book book3 = new Book("456787654343", "Head First Java", authors3);
        bookStorage.add(book3);


        List<Author> authors4 =createListOfAuthors(new Author("Craig", "Walls"));
        Book book4 = new Book("8654345456678", "Spring in Action", authors4);
        bookStorage.add(book4);


        List<Author> authors5 =createListOfAuthors(new Author("Raoul Gabriel", "Urma"));
        Book book5 = new Book("776554434", "Java 8 in Action", authors5);
        bookStorage.add(book5);

    }

    private static List<Author> createListOfAuthors(Author... authors) {
        List<Author> result = new ArrayList<>();
        for (Author a : authors) {
            result.add(a);
        }
        return result;
    }
}

