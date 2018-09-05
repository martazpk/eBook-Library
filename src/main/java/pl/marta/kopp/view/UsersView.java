package pl.marta.kopp.view;

import pl.marta.kopp.library.BorrowingController;
import pl.marta.kopp.library.BorrowingView;
import pl.marta.kopp.library.ReturnController;
import pl.marta.kopp.library.ReturnView;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BookStorageJpa;
import pl.marta.kopp.persistence.BorrowStorage;
import pl.marta.kopp.persistence.BorrowStorageJpa;

public class UsersView {
    private final SystemInterface systemInterface;
    private final UsersMenu usersMenu;
    private final BorrowingView borrowingView;
    private final ReturnView returnView;
    private final PresentBooksView presentBooksView;


    public UsersView(SystemInterface systemInterface, BorrowingController borrowingController, ReturnController returnController) {
        this.systemInterface = systemInterface;
        this.usersMenu = new UsersMenu(systemInterface);
        this.borrowingView = new BorrowingView(systemInterface, borrowingController);
        this.returnView = new ReturnView(systemInterface, returnController);
        this.presentBooksView=new PresentBooksView(systemInterface,borrowingController);
    }

    public void show() {
        try {
            int option = Integer.parseInt(systemInterface.read());

            if (option == 1) {
                presentBooksView.show();
                borrowingView.show();

            } else if (option == 2) {
                returnView.show();

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
