package pl.marta.kopp.view;

import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.library.*;

public class UsersView {
    private final SystemInterface systemInterface;
    private final UsersMenu usersMenu;
    private final BorrowingView borrowingView;
    private final ReturnView returnView;
    private final AvailableBooksView availableBooksView;


    public UsersView(SystemInterface systemInterface, BorrowingController borrowingController,
                     ReturnController returnController, AvailableBooksView availableBooksView) {
        this.systemInterface = systemInterface;
        this.usersMenu = new UsersMenu(systemInterface);
        this.availableBooksView = availableBooksView;
        this.borrowingView = new BorrowingView(systemInterface, borrowingController);
        this.returnView = new ReturnView(systemInterface, returnController);

    }

    public void show(User user) {
        usersMenu.show();
        try {
            int option = Integer.parseInt(systemInterface.read());

            if (option == 1) {
                borrowingView.show(user);
                show(user);

            } else if (option == 2) {
                returnView.show(user);
                show(user);


            } else if (option == 3) {
                availableBooksView.show();
                show(user);

            } else if (option == 5) {
                System.exit(0);
            } else {
                aFailureChoice();
            }
        } catch (NumberFormatException e) {
            aFailureChoice();
        }
    }

    private void aFailureChoice() {
        systemInterface.display("Niepoprawny wybór. Wybierz ponownie");
        usersMenu.show();
    }
}
