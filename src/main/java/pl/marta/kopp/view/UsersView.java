package pl.marta.kopp.view;

import pl.marta.kopp.view.SystemInterface;

public class UsersView {
    private final SystemInterface systemInterface;

    public UsersView(SystemInterface systemInterface) {
        this.systemInterface = systemInterface;

    }

    public void show() {
        systemInterface.display("-------------------------------------------");
        systemInterface.display("                 Hello                     ");
        systemInterface.display("                                           ");
        systemInterface.display("             Have a nice day!              ");
        systemInterface.display("-------------------------------------------");

    }
}
