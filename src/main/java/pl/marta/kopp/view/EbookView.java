package pl.marta.kopp.view;

import pl.marta.kopp.service.UserStorageJpa;
import pl.marta.kopp.login.LoginController;
import pl.marta.kopp.login.LoginView;
import pl.marta.kopp.registration.RegistrationController;
import pl.marta.kopp.registration.RegistrationView;

public class EbookView {
    private final SystemInterface systemInterface;
    private final MainMenu mainMenu;
    private final LoginView loginView;
    private final RegistrationView registrationView;


    public EbookView(SystemInterface systemInterface, UserStorageJpa userStorageJpa) {
        this.systemInterface = systemInterface;
        mainMenu = new MainMenu();
        loginView = new LoginView(systemInterface, new LoginController(userStorageJpa));
        registrationView = new RegistrationView(systemInterface, new RegistrationController(userStorageJpa));
    }

    public void show() {
        mainMenu.show();
        try {
            int option = Integer.parseInt(systemInterface.read());
            if (option == 1) {
                loginView.show();

            } else if (option == 2) {
                registrationView.show();
                show();
            } else if (option == 3) {
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
        mainMenu.show();
    }
}
