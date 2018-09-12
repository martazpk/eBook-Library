package pl.marta.kopp.library;

public class BorrowDoesNotExistException extends RuntimeException {
    public BorrowDoesNotExistException(long id) {
        super("borrow "+id+" does not exist");
    }
}
