package pl.marta.kopp.library;

import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.view.SystemInterface;

import java.util.List;

public class BorrowedBooksByUserView {
    private final SystemInterface systemInterface;
    private final BorrowingController controller;

    public BorrowedBooksByUserView(SystemInterface systemInterface, BorrowingController controller) {
        this.systemInterface = systemInterface;
        this.controller = controller;
    }

    public void show(User user) {
        systemInterface.display("");
        systemInterface.display("Twoje obecne wypożyczenia:");
        List<Book> books = controller.getBorrowedBookdByUserId(user.getId());
        for (Book b : books) {
            systemInterface.display(b.toString());
        }
    }
}
