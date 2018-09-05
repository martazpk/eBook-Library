package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.borrow.Borrow;

import javax.persistence.*;
import java.util.List;

public class BorrowStorageJpa  implements BorrowStorage{
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("eBookPersistence");
    EntityManager entityManager = factory.createEntityManager();
    @Override
    public void add(long bookId, long userId) {
        Borrow borrow=new Borrow.Builder().bookId(bookId).userId(userId).build();
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

    @Override
    public Borrow getByBookId(long bookId) {
        TypedQuery<Borrow> query = entityManager.createQuery("FROM Borrow b WHERE b.bookId=:bookId", Borrow.class);
        query.setParameter("bookId", bookId);
        return query.getSingleResult();
    }
    @Override
    public List<Borrow> getByUserId(long userId) {
        TypedQuery<Borrow> query = entityManager.createQuery("FROM Borrow b WHERE b.userId=:userId", Borrow.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
//    public List<Long> getBookIdByUserId(long userId) {
//        TypedQuery<Long> query = entityManager.createQuery("SELECT b.bookId FROM Borrow b WHERE b.bookId=:bookId", Long.class);
//        query.setParameter("userId", userId);
//        return query.getResultList();
//    }
}
