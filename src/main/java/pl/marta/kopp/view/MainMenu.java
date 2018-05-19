package pl.marta.kopp.view;

import java.util.Scanner;

public class MainMenu {
    private final SystemInterface systemInterface=new CliSystemInterface(new Scanner(System.in));

    void show(){
        systemInterface.display("*********************************************");
        systemInterface.display("*   Witaj w mojej aplikacji e-Book Library  *");
        systemInterface.display("*                                           *");
        systemInterface.display("*       Wciśnij 1 aby się zalogować         *");
        systemInterface.display("*      Wciśnij 2 aby się zarejestrować      *");
        systemInterface.display("*      Wciśnij 3 aby wyjść z aplikacji      *");
        systemInterface.display("*                                           *");
        systemInterface.display("*********************************************");
    }
}
