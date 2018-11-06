package pl.marta.kopp.view;


public class UsersMenu {
    private final SystemInterface systemInterface;

    public UsersMenu(SystemInterface systemInterface) {
        this.systemInterface = systemInterface;
    }

    public void show() {
        systemInterface.display("---------------------------------------------");
        systemInterface.display(" Wciśnij 1 aby wypożyczyć książkę            ");
        systemInterface.display(" Wciśnij 2 zwrócić ksiązkę                   ");
        systemInterface.display(" Wciśnij 3 aby zobaczyć dostępne książki     ");
        systemInterface.display("                                             ");
        systemInterface.display("  Aby wyjść z aplikacji wciśnij 5            ");
        systemInterface.display("---------------------------------------------");

    }
}

