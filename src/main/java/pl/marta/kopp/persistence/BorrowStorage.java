package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.borrow.Borrow;

import java.util.List;

public interface BorrowStorage {
    void add(long bookId,long userId);
    void delete(long id);
    boolean isExistsUserId(long id);
    boolean isExistsBookId(long id);
    Borrow getByBookId(long id);
    List<Borrow> getByUserId(long userId);

}
