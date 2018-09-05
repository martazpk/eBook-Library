package pl.marta.kopp.library;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.user.User;
import pl.marta.kopp.view.SystemInterface;

public class BorrowingView {
    private final SystemInterface systemInterface;
    private final BorrowingController borrowingController;

    public BorrowingView(SystemInterface systemInterface, BorrowingController controller) {
        this.systemInterface = systemInterface;
        this.borrowingController = controller;
    }

    public void show(User user) {
        try {
            systemInterface.display("");
            systemInterface.display("Podaj Id książki, którą chcesz pożyczyć: ");
            int id = Integer.parseInt(systemInterface.read());
            Response response = borrowingController.borrowBook(id, user.getId());
            if (response.getSuccess()) {
                systemInterface.display("");
                systemInterface.display("Udało się wypożyczyć książkę  " + borrowingController.getById(id));

            } else {
                systemInterface.display("Błąd: " + response.getMessage());
                show(user);
            }
        } catch (NumberFormatException e) {
            aFailureChoice(user);
        }
    }

    private void aFailureChoice(User user) {
        systemInterface.display("Niepoprawny wybór. Wybierz ponownie");
        show(user);
    }
}
