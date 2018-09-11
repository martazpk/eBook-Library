package pl.marta.kopp.domain.service.exception;

public class BookAlreadyExistException extends RuntimeException{
    public BookAlreadyExistException(String isbn) {
        super("ISBN "+isbn+" already exists");
    }
}
