package pl.marta.kopp.library;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.domain.model.Borrowing;
import pl.marta.kopp.domain.service.BookService;
import pl.marta.kopp.domain.service.BorrowingService;
import pl.marta.kopp.domain.service.exception.BookDoesNotExistException;

import java.util.List;

public class BorrowingController {
    private final BookService bookService;
    private final BorrowingService borrowingService;

    public BorrowingController(BookService bookService, BorrowingService borrowingService) {
        this.bookService = bookService;
        this.borrowingService = borrowingService;
    }


     Response borrowBook(long bookId, long userId) {

        if (bookService.isExists(bookId)) {
            Book book = bookService.getById(bookId);
            if (book.isBorrow()) return Response.aFailureResponse("Sorry, this book is already borrowed");
            else bookService.setBorrow(bookId,true);
            borrowingService.add(bookId,userId);
            return Response.aSuccessfulResponse();
        }
        return Response.aFailureResponse("Sorry, this book is not our catalog.");
    }

}
