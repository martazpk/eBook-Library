package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.borrow.Borrow;

public interface BorrowStorage {
    void add(long bookId,long userId);
    void delete(long id);
    boolean isExistsUserId(long id);
    boolean isExistsBookId(long id);
    Borrow getByBookId(long id);



}
