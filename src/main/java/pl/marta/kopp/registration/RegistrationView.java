package pl.marta.kopp.registration;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.view.SystemInterface;

public class RegistrationView {
    private final RegistrationController registrationController;
    private final SystemInterface systemInterface;

    public RegistrationView(SystemInterface systemInterface, RegistrationController registrationController) {
        this.systemInterface = systemInterface;
        this.registrationController = registrationController;
    }

    public void show(){
        systemInterface.display("Podaj login: ");
        String login=systemInterface.read();
        systemInterface.display("Podaj hasło: ");
        String password=systemInterface.read();

        Response response=registrationController.createUser(login,password);
        if(response.getSuccess()){
            systemInterface.display("Użytkownik "+login+" został pomyślnie zarejestrowany");


        }else {
            systemInterface.display("Rejestracja się nie powiodła "+response.getMessage());
            show();
        }
    }
}
