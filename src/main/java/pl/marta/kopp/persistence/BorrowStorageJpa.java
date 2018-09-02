package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.domain.boorow.Borrow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BorrowStorageJpa  implements BorrowStorage{
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("eBookPersistence");
    EntityManager entityManager = factory.createEntityManager();
    @Override
    public void add(Borrow borrow) {
        entityManager.getTransaction().begin();
        entityManager.persist(borrow);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        entityManager.getTransaction().begin();
        Borrow borrow = entityManager.find(Borrow.class, id);
        entityManager.remove(borrow);
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean isExistsUserId(long userId) {
        Query query = entityManager.createQuery("FROM Borrow b WHERE b.userId=:userId");
        query.setParameter("userId", userId);
        return !(query.getResultList().isEmpty());
    }

    @Override
    public boolean isExistsBookId(long bookId) {
        Query query = entityManager.createQuery("FROM Borrow b WHERE b.bookId=:bookId");
        query.setParameter("bookId", bookId);
        return !(query.getResultList().isEmpty());
    }
}
