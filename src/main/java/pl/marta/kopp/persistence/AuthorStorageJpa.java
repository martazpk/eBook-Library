package pl.marta.kopp.persistence;


import pl.marta.kopp.domain.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AuthorStorageJpa implements AuthorStorage {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("eBookPersistence");
    private EntityManager entityManager = factory.createEntityManager();


    @Override
    public void add(Author author) {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        entityManager.getTransaction().commit();
    }

    @Override
    public Author get(long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public boolean isExists(Author author) {
        Query query = entityManager.createQuery("FROM Author a WHERE a.name=:name AND a.surname=:surname ");
        query.setParameter("name", author.getName());
        query.setParameter("surname", author.getSurname());
        return !(query.getResultList().isEmpty());
    }
    @Override
    public boolean isExists(long id){
        Query query = entityManager.createQuery("FROM Author a WHERE a.id=:id");
        query.setParameter("id", id);
        return !(query.getResultList().isEmpty());
    }
}
