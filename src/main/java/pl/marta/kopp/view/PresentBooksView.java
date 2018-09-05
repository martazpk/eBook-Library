package pl.marta.kopp.view;

import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.library.BorrowingController;
import pl.marta.kopp.persistence.BookStorage;

import java.util.List;

public class PresentBooksView {
    private final SystemInterface systemInterface;
    private final BorrowingController borrowingController;

    public PresentBooksView(SystemInterface systemInterface, BorrowingController borrowingController) {
        this.systemInterface = systemInterface;
        this.borrowingController = borrowingController;
    }

    public void show() {
        systemInterface.display("");
        systemInterface.display("Dostępne książki:");
        List<Book> presentBooks = borrowingController.getPresentBooks();
        for (Book b : presentBooks) {
            systemInterface.display(b.toString());
        }
    }
}
