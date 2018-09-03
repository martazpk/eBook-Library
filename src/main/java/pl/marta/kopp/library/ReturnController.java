package pl.marta.kopp.library;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.domain.borrow.Borrow;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BorrowStorage;

public class ReturnController {
    private final BookStorage bookStorage;
    private final BorrowStorage borrowStorage;

    public ReturnController(BookStorage bookStorage, BorrowStorage borrowStorage) {
        this.bookStorage = bookStorage;
        this.borrowStorage = borrowStorage;
    }

    public Response returnBook(long bookId, long userId) {
        if (borrowStorage.isExistsUserId(userId)) {
            if (borrowStorage.isExistsBookId(bookId)) {
                deleteBorrow(bookId);
                setBookBorrow(bookId);
                return Response.aSuccessfulResponse();
            } else return Response.aFailureResponse("You don't have any borrowed book.");
        } else return Response.aFailureResponse("Invalid Book Id");
    }

    private void deleteBorrow(long bookId) {
        Borrow borrow = borrowStorage.getByBookId(bookId);
        borrowStorage.delete(borrow.getId());
    }

    private void setBookBorrow(long bookId) {
        Book book = bookStorage.getById(bookId);
        book.setBorrow(false);
        bookStorage.update(book);
    }
}


