package pl.marta.kopp.view;

import java.util.Scanner;

public class CliSystemInterface implements SystemInterface {
    private  Scanner scanner;

    public CliSystemInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String read() {
        return scanner.next();
    }

    @Override
    public void display(String message) {
          System.out.println(message);
    }
}
