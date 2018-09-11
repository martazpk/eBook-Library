package pl.marta.kopp.library;

import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.view.SystemInterface;

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
