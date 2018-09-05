package pl.marta.kopp.library;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.user.User;
import pl.marta.kopp.view.SystemInterface;

public class ReturnView {
    private final SystemInterface systemInterface;
    private final ReturnController controller;

    public ReturnView(SystemInterface systemInterface, ReturnController controller) {
        this.systemInterface = systemInterface;
        this.controller = controller;
    }


    public void show(User user) {
        try {
            systemInterface.display("");
            systemInterface.display("Podaj Id książki, którą chcesz zwrócić: ");
            int id = Integer.parseInt(systemInterface.read());
            Response response = controller.returnBook(id, user.getId());
            if (response.getSuccess()) {
                systemInterface.display("");
                systemInterface.display("Udało się zwrócić książkę  " + controller.getBookById(id));

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
