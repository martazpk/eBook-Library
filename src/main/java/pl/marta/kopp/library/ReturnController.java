package pl.marta.kopp.library;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.domain.borrow.Borrow;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BorrowStorage;

import java.util.ArrayList;
import java.util.List;

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
                bookStorage.setBorrow(bookId, false);
                return Response.aSuccessfulResponse();
            } else return Response.aFailureResponse("You don't have any borrowed book.");
        } else return Response.aFailureResponse("Invalid Book Id");
    }

    private void deleteBorrow(long bookId) {
        if (borrowStorage.isExistsBookId(bookId)) {
            Borrow borrow = borrowStorage.getByBookId(bookId);
            borrowStorage.delete(borrow.getId());
        } else throw new BorrowDoesNotExistsException();
    }

    private List<Borrow> getBorrowByUserId(long userId) {
        if (borrowStorage.isExistsUserId(userId)) {
            return borrowStorage.getByUserId(userId);
        } else throw new BorrowDoesNotExistsException();
    }

    public List<Book> getBorrowedBooksByUserId(long userId) {
        List<Borrow> borrows = getBorrowByUserId(userId);
        List<Book> books = new ArrayList<>();
        for (Borrow b : borrows) {
            Book bookById = bookStorage.getById(b.getBookId());
            books.add(bookById);
        }
        return books;
    }

    public Book getBookById(int id) {
        if(bookStorage.isExists(id))
            return bookStorage.getById(id);
        else throw new BookDoesNotExistsException(id);
    }
}


