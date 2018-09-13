package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.model.Borrowing;

import java.util.List;

public interface BorrowStorage {
    void add(Borrowing borrowing);
    void delete(long id);
    boolean isExists(long id);
    boolean isExistsUserId(long id);
    boolean isExistsBookId(long id);
    Borrowing getByBookId(long id);
    List<Borrowing> getCurrentByUserId(long userId);

    void update(Borrowing borrowing);
}
