package pl.marta.kopp.CreateDataBase;

import pl.marta.kopp.domain.Author;
import pl.marta.kopp.domain.Book;
import pl.marta.kopp.service.AuthorStorage;
import pl.marta.kopp.service.BookStorage;

import java.util.ArrayList;
import java.util.List;

public class CreateBookStorage {
    public static void create(BookStorage bookStorage, AuthorStorage authorStorage){

        Author author = new Author("Jan", "Brzechwa");
        List<Author> authors=new ArrayList<>();
        authors.add(author);
        Book book = new Book("Akademia Pana Kleksa",authors);
        authorStorage.add(author);
        bookStorage.add(book);

        Author author1 = new Author("Henryk", "Sienkiewicz");
        List<Author> authors1=new ArrayList<>();
        authors1.add(author1);
        Book book1 = new Book("W Pustyni i w Puszczy",authors1);
        authorStorage.add(author1);
        bookStorage.add(book1);

        Author author2 = new Author("Adam", "Mickiewicz");
        List<Author> authors2=new ArrayList<>();
        authors2.add(author1);
        Book book2 = new Book("Dziady",authors2);
        authorStorage.add(author2);
        bookStorage.add(book2);

        Author author3 = new Author("Julian", "Tuwim");
        List<Author> authors3=new ArrayList<>();
        authors3.add(author3);
        Book book3 = new Book("Wiersze",authors3);
        authorStorage.add(author3);
        bookStorage.add(book3);

        Author author4 = new Author("Bolesław", "Prus");
        List<Author> authors4=new ArrayList<>();
        authors4.add(author4);
        Book book4 = new Book("Katarynka",authors4);
        authorStorage.add(author4);
        bookStorage.add(book4);

        Author author5 = new Author("Aleksander", "Fredro");
        List<Author> authors5=new ArrayList<>();
        authors5.add(author5);
        Book book5 = new Book("Zemsta",authors5);
        authorStorage.add(author5);
        bookStorage.add(book5);


    }
}
