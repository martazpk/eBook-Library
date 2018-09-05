package pl.marta.kopp.persistence;


import pl.marta.kopp.domain.user.User;

import javax.persistence.*;


public class UserStorageJpa implements UserStorage {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("eBookPersistence");
    EntityManager entityManager = factory.createEntityManager();


    @Override
    public boolean isUserExists(String login) {
        Query query = entityManager.createQuery("FROM User u WHERE u.login=:login");
        query.setParameter("login", login);
        return !(query.getResultList().isEmpty());
    }

    @Override
    public boolean isUserExists(String login, String password) {
        Query query = entityManager.createQuery("FROM User u WHERE u.login=:login AND u.password=:password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        return !(query.getResultList().isEmpty());
    }

    @Override
    public void addUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);

    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE u.login=:login AND u.password=:password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        return query.getSingleResult();

    }
}
