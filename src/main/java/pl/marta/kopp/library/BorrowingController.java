package pl.marta.kopp.library;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.model.Book;
import pl.marta.kopp.domain.model.Borrowing;
import pl.marta.kopp.domain.service.BookService;
import pl.marta.kopp.domain.service.BorrowingService;
import pl.marta.kopp.domain.service.exception.BookDoesNotExistException;
import pl.marta.kopp.domain.service.exception.UserDoesNotExistException;

import java.util.List;

public class BorrowingController {
    private final BookService bookService;
    private final BorrowingService borrowingService;

    public BorrowingController(BookService bookService, BorrowingService borrowingService) {
        this.bookService = bookService;
        this.borrowingService = borrowingService;
    }


    public Response borrowBook(long bookId, long userId) {

        if (bookService.isExists(bookId)) {
            Book book = bookService.getById(bookId);
            if (book.isBorrow()) return Response.aFailureResponse("Sorry, this book is already borrowed");
            else bookService.setBorrow(bookId,true);
            borrowingService.add(bookId,userId);
            return Response.aSuccessfulResponse();
        }
        return Response.aFailureResponse("Sorry, this book is not our catalog.");
    }

    public Response returnBook(long bookId) {
        try {
            Borrowing borrowing = borrowingService.getByBookId(bookId);
            borrowingService.delete(borrowing.getId());
            bookService.setBorrow(bookId, false);
            return Response.aSuccessfulResponse();
        } catch (BookDoesNotExistException exc) {
            return Response.aFailureResponse("Invalid Book Id");
        }
    }

    public List<Book> getPresentBooks() {
        return bookService.getPresentBooks();
    }

    public Book getByBookId(int bookId) {
      return bookService.getById(bookId);
    }
    List<Book>getBorrowedBookdByUserId(long userId){
        return borrowingService.getBorrowedBooksByUserId(userId);
    }
}
