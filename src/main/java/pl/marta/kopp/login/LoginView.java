package pl.marta.kopp.login;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.user.User;
import pl.marta.kopp.library.BorrowingController;
import pl.marta.kopp.library.ReturnController;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BookStorageJpa;
import pl.marta.kopp.persistence.BorrowStorage;
import pl.marta.kopp.persistence.BorrowStorageJpa;
import pl.marta.kopp.view.UsersMenu;
import pl.marta.kopp.view.SystemInterface;
import pl.marta.kopp.view.UsersView;

public class LoginView {
    private final SystemInterface systemInterface;
    private final LoginController controller;
    private final UsersMenu usersMenu;
    private final UsersView usersView;


    public LoginView(SystemInterface systemInterface, LoginController controller, BorrowingController borrowingController, ReturnController returnController) {
        this.systemInterface = systemInterface;
        this.controller = controller;
        usersMenu = new UsersMenu(systemInterface);
        this.usersView=new UsersView(systemInterface,borrowingController,returnController);

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
            usersMenu.show();
            usersView.show(controller.getUser(login,password));

        } else {
            systemInterface.display("Błędne logowanie " + response.getMessage());
            show();
        }

    }
}
