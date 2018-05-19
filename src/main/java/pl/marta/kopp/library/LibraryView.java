package pl.marta.kopp.library;

import pl.marta.kopp.view.SystemInterface;

public class LibraryView {
    private final SystemInterface systemInterface;

    public LibraryView(SystemInterface systemInterface) {
        this.systemInterface = systemInterface;

    }

    public void show() {
        systemInterface.display("-------------------------------------------");
        systemInterface.display("Wciśnij 1 aby wyświetlić wszystkie książki");
        systemInterface.display("       Wciśnij 2 aby się wylogować");
        systemInterface.display("      Wciśnij 3 aby wyjść z aplikacji");
        systemInterface.display("-------------------------------------------");

    }
}
