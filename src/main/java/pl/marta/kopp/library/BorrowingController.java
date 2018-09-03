package pl.marta.kopp.library;

import pl.marta.kopp.communication.Response;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.domain.borrow.Borrow;
import pl.marta.kopp.persistence.BookStorage;
import pl.marta.kopp.persistence.BorrowStorage;

public class BorrowingController {
    private final BookStorage bookStorage;
    private final BorrowStorage borrowStorage;

    public BorrowingController(BookStorage bookStorage, BorrowStorage borrowStorage) {
        this.bookStorage = bookStorage;
        this.borrowStorage = borrowStorage;
    }

    public Response borrowBook(long bookId, long userId) {
        if (bookStorage.isExists(bookId)) {
            Book book = bookStorage.getById(bookId);
            if (book.isBorrow()) return Response.aFailureResponse("Sorry, this book is already borrowed");
            else setBookBorrow(book);
            addNewBorrow(bookId, userId);
            return Response.aSuccessfulResponse();
        }
        return Response.aFailureResponse("Sorry, this book is not our catalog.");
    }

    private void setBookBorrow(Book book) {
        book.setBorrow(true);
        bookStorage.update(book);
    }

    private void addNewBorrow(long bookId, long userId) {
        Borrow newBorrow = new Borrow.Builder().bookId(bookId).userId(userId).build();
        borrowStorage.add(newBorrow);
    }
}
