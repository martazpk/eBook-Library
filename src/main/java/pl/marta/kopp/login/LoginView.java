package pl.marta.kopp.login;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.view.UsersView;
import pl.marta.kopp.view.SystemInterface;

public class LoginView {
    private final SystemInterface systemInterface;
    private final LoginController controller;
    private final UsersView libraryView;


    public LoginView(SystemInterface systemInterface, LoginController controller) {
        this.systemInterface = systemInterface;
        this.controller = controller;
        libraryView=new UsersView(systemInterface);
    }

    public void show(){
        systemInterface.display("Podaj login: ");
        String login=systemInterface.read();
        systemInterface.display("Podaj hasło: ");
        String password=systemInterface.read();

        Response response=controller.login(login,password);
        if(response.getSuccess()){
            systemInterface.display("Witaj "+login);
          libraryView.show();

        }else {
            systemInterface.display("Błędne logowanie "+response.getMessage());
            show();
        }
    }
}
