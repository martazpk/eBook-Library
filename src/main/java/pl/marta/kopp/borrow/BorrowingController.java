package pl.marta.kopp.borrow;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.Book;
import pl.marta.kopp.persistence.BookStorage;

public class BorrowingController {
    private final BookStorage bookStorage;

    public BorrowingController(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }
    public Response borrowBook(long id){
        Book book = bookStorage.getById(id);
        if(book==null){ return Response.aFailureResponse("Sorry, this book is not our catalog.");}
        else {
            if(book.isBorrow()) {return Response.aFailureResponse("Sorry, this book is already borrowed");}
            else return Response.aSuccessfulResponse();
        }
    }
}
