package pl.marta.kopp.borrow;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.domain.boorow.Borrow;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BorrowStorage;
import pl.marta.kopp.persistence.UserStorage;

public class BorrowingController {
    private final BookStorage bookStorage;
    private final BorrowStorage borrowStorage;

    public BorrowingController(BookStorage bookStorage, BorrowStorage borrowStorage) {
        this.bookStorage = bookStorage;
        this.borrowStorage = borrowStorage;
    }
    public Response borrowBook(long bookId,long userId){
        Book book = bookStorage.getById(bookId);
        if(book==null){ return Response.aFailureResponse("Sorry, this book is not our catalog.");}
        else {
            if(book.isBorrow()) {return Response.aFailureResponse("Sorry, this book is already borrowed");}
            else borrow(bookId,userId);
                return Response.aSuccessfulResponse();
        }
    }

    private void borrow(long bookId,long userId){
        bookStorage.setBorrow(bookId);
        Borrow newBorrow= new Borrow(bookId,userId);
        borrowStorage.add(newBorrow);
    }
}
