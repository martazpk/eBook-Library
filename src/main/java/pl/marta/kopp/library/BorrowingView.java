package pl.marta.kopp.library;

import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BookStorageJpa;
import pl.marta.kopp.view.PresentBooksView;
import pl.marta.kopp.view.SystemInterface;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BorrowingView {
    private final SystemInterface systemInterface;
    private final BorrowingController borrowingController;


    public BorrowingView(SystemInterface systemInterface, BorrowingController borrowingController ) {
        this.systemInterface = systemInterface;
        this.borrowingController = borrowingController;
    }

    public void show() {

    }
}
