package pl.marta.kopp;


import pl.marta.kopp.configuration.InitConstraints;
import pl.marta.kopp.domain.service.BookService;
import pl.marta.kopp.domain.service.BorrowingService;
import pl.marta.kopp.domain.service.UserService;
import pl.marta.kopp.library.BorrowingController;
import pl.marta.kopp.login.LoginController;
import pl.marta.kopp.persistence.*;
import pl.marta.kopp.registration.RegistrationController;
import pl.marta.kopp.view.CliSystemInterface;
import pl.marta.kopp.view.EbookView;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        UserStorage userStorage = new UserStorageJpa();
        BookStorageJpa bookStorage = new BookStorageJpa();
        BorrowStorage borrowStorage = new BorrowStorageJpa();

        UserService userService=new UserService(userStorage);
        BookService bookService=new BookService(bookStorage);
        BorrowingService borrowingService=new BorrowingService(borrowStorage,userStorage,bookStorage);

        InitConstraints.create(bookStorage);

        new EbookView(new CliSystemInterface(new Scanner(System.in)), new LoginController(userStorage),
                new BorrowingController(bookService, borrowingService),
                new RegistrationController(userStorage)).show();

    }
}
