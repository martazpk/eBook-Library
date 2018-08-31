package pl.marta.kopp.service;


import pl.marta.kopp.domain.Author;
import pl.marta.kopp.domain.AuthorAlreadyExistsException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AuthorStorageJpa implements AuthorStorage {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("eBookPersistence");
    EntityManager entityManager = factory.createEntityManager();


    @Override
    public void add(Author author) {

        if (!isExists(author)) {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();
        } else  throw new AuthorAlreadyExistsException(author);
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
        Query query = entityManager.createQuery("from Author a where a.name=:name AND a.surname=:surname ");
        query.setParameter("name", author.getName());
        query.setParameter("surname", author.getSurname());
        return !(query.getResultList().isEmpty());
    }
}
