package pl.marta.kopp.library;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.model.Borrowing;
import pl.marta.kopp.domain.service.BookService;
import pl.marta.kopp.domain.service.BorrowingService;

public class ReturnController {
    private final BookService bookService;
    private final BorrowingService borrowingService;

    public ReturnController(BookService bookService, BorrowingService borrowingService) {
        this.bookService = bookService;
        this.borrowingService = borrowingService;
    }

     Response returnBook(long userId,long bookId) {
       if(borrowingService.isExists(bookId)){
           Borrowing borrowing = borrowingService.getByBookId(bookId);
           if(borrowing.getUserId()==userId){
               borrowingService.delete(borrowing.getId());
               bookService.setBorrow(bookId,false);
               return Response.aSuccessfulResponse();
           }
               return Response.aFailureResponse("Invalid user id");
       }
       return Response.aFailureResponse("Invalid Book Id");

    }
}
