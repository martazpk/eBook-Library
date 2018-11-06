package pl.marta.kopp.login;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.library.BorrowingController;
import pl.marta.kopp.library.ReturnController;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.view.AvailableBooksView;
import pl.marta.kopp.view.UsersMenu;
import pl.marta.kopp.view.SystemInterface;
import pl.marta.kopp.view.UsersView;

public class LoginView {
    private final SystemInterface systemInterface;
    private final LoginController controller;
    private final UsersView usersView;


    public LoginView(SystemInterface systemInterface, LoginController controller,
                     BorrowingController borrowingController, ReturnController returnController, BookStorage bookStorage) {
        this.systemInterface = systemInterface;
        this.controller = controller;
        UsersMenu usersMenu = new UsersMenu(systemInterface);
        AvailableBooksView availableBooksView=new AvailableBooksView(systemInterface,bookStorage);
        this.usersView=new UsersView(systemInterface,borrowingController,returnController, availableBooksView);

    }

    public void show() {
        systemInterface.display("Podaj login: ");
        String login = systemInterface.read();
        systemInterface.display("Podaj hasło: ");
        String password = systemInterface.read();

        Response response = controller.login(login, password);
        if (response.getSuccess()) {
            systemInterface.display("");
            systemInterface.display("Witaj " + login);
            usersView.show(controller.getUser(login,password));

        } else {
            systemInterface.display("Błędne logowanie " + response.getMessage());
            show();
        }

    }
}
