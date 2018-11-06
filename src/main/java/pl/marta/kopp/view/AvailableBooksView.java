package pl.marta.kopp.view;

import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.persistence.BookStorage;

import java.util.List;

public class AvailableBooksView {
    private final SystemInterface systemInterface;
    private final BookStorage bookStorage;

    public AvailableBooksView(SystemInterface systemInterface, BookStorage bookStorage) {
        this.systemInterface = systemInterface;
        this.bookStorage=bookStorage;
    }

    public void show(){
        List<Book> presentBooks = bookStorage.getPresentBooks();
        for (Book b:presentBooks) {
         systemInterface.display(b.toString());
        }

    }
}
