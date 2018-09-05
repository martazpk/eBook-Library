package pl.marta.kopp.view;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.persistence.BookStorage;

import java.util.List;

public class PresentBooksView {
    private final SystemInterface systemInterface;
    private final BookStorage bookStorage;

    public PresentBooksView(SystemInterface systemInterface, BookStorage bookStorage) {
        this.systemInterface = systemInterface;
        this.bookStorage=bookStorage;
    }

    public void show(){
        systemInterface.display("PresentBooksView success");

        List<Book> presentBooks=bookStorage.getAll();
        System.out.println(presentBooks.size());
        for (Book b:presentBooks) {
            systemInterface.display(b.toString());
        }
    }
}
