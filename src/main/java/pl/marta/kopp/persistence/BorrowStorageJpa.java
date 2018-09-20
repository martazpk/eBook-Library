package pl.marta.kopp.persistence;

import pl.marta.kopp.domain.model.Borrowing;

import javax.persistence.*;
import java.util.List;

public class BorrowStorageJpa  implements BorrowStorage{
    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("eBookPersistence");
    private EntityManager entityManager= factory.createEntityManager();

    @Override
    public void add(Borrowing borrowing) {

        entityManager.getTransaction().begin();
        entityManager.persist(borrowing);
        entityManager.getTransaction().commit();

    }

    @Override
    public void delete(long id) {
        entityManager.getTransaction().begin();
        Borrowing borrowing = entityManager.find(Borrowing.class, id);
        entityManager.remove(borrowing);
        entityManager.getTransaction().commit();
    }
    @Override
    public void update(Borrowing borrowing) {
        entityManager.getTransaction().begin();
        entityManager.merge(borrowing);
        entityManager.getTransaction().commit();
    }
    @Override
    public boolean isExistsUserId(long userId) {
        Query query = entityManager.createQuery("FROM Borrowing b WHERE b.userId=:userId");
        query.setParameter("userId", userId);
        return !(query.getResultList().isEmpty());
    }

    @Override
    public boolean isExistsBookId(long bookId) {
        Query query = entityManager.createQuery("FROM Borrowing b WHERE b.bookId=:bookId");
        query.setParameter("bookId", bookId);
        return !(query.getResultList().isEmpty());
    }

    @Override
    public Borrowing getByBookId(long bookId) {
        TypedQuery<Borrowing> query = entityManager.createQuery("FROM Borrowing b WHERE b.bookId=:bookId", Borrowing.class);
        query.setParameter("bookId", bookId);
        return query.getSingleResult();
    }
    @Override
    public List<Borrowing> getListByUserId(long userId) {
        TypedQuery<Borrowing> query = entityManager.createQuery("FROM Borrowing b WHERE b.userId=:userId ", Borrowing.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }


    @Override
    public boolean isExists(long id){
        Query query = entityManager.createQuery("FROM Borrowing b WHERE b.id=:id");
        query.setParameter("id", id);
        return !(query.getResultList().isEmpty());
    }
}
