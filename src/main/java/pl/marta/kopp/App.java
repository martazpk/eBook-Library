package pl.marta.kopp;


import pl.marta.kopp.CreateDataBase.CreateBookStorage;
import pl.marta.kopp.domain.author.Author;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.library.BorrowingController;
import pl.marta.kopp.library.ReturnController;
import pl.marta.kopp.login.LoginController;
import pl.marta.kopp.persistence.*;
import pl.marta.kopp.registration.RegistrationController;
import pl.marta.kopp.view.CliSystemInterface;
import pl.marta.kopp.view.EbookView;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        UserStorage userStorage = new UserStorageJpa();
        BookStorage bookStorage = new BookStorageJpa();
        BorrowStorage borrowStorage = new BorrowStorageJpa();

        CreateBookStorage.create(bookStorage);

        new EbookView(new CliSystemInterface(new Scanner(System.in)), new LoginController(userStorage),
                new BorrowingController(bookStorage, borrowStorage),
                new ReturnController(bookStorage, borrowStorage),
                new RegistrationController(userStorage)).show();


    }
}
