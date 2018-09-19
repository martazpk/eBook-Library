package pl.marta.kopp.view;

import pl.marta.kopp.domain.model.User;
import pl.marta.kopp.library.*;

public class UsersView {
    private final SystemInterface systemInterface;
    private final UsersMenu usersMenu;
    private final BorrowingView borrowingView;
    private final ReturnView returnView;
    private final PresentBooksView presentBooksView;
    private final BorrowedBooksByUserView borrowedBooksByUserView;


    public UsersView(SystemInterface systemInterface, BorrowingController borrowingController,ReturnController returnController) {
        this.systemInterface = systemInterface;
        this.usersMenu = new UsersMenu(systemInterface);
        this.borrowingView = new BorrowingView(systemInterface, borrowingController);
        this.returnView = new ReturnView(systemInterface,returnController);
        this.presentBooksView=new PresentBooksView(systemInterface,borrowingController);
        this.borrowedBooksByUserView=new BorrowedBooksByUserView(systemInterface,borrowingController);
    }

    public void show(User user) {
        usersMenu.show();
        try {
            int option = Integer.parseInt(systemInterface.read());

            if (option == 1) {
                presentBooksView.show();
                borrowingView.show(user);
                show(user);

            } else if (option == 2) {
                borrowedBooksByUserView.show(user);
                returnView.show(user);
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
