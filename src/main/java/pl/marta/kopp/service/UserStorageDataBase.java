package pl.marta.kopp.service;

import org.hibernate.Query;
import org.hibernate.Session;
import pl.marta.kopp.connector.DBConnector;
import pl.marta.kopp.domain.User;


public class UserStorageDataBase implements UserStorage {
    private DBConnector dbConnector;

    public UserStorageDataBase(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    @Override
    public boolean isUserExists(String login) {
        Session session = dbConnector.getSession();
        Query query = session.createQuery("from User where login=:login");
        query.setParameter("login", login);
        return !(query.list().isEmpty());
    }

    @Override
    public boolean isUserExists(String login, String password) {
        Session session = dbConnector.getSession();
        Query query = session.createQuery("from User where login=:login AND password=:password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        return !(query.list().isEmpty());
    }
    @Override
    public void addUser(User user) {
        Session session = dbConnector.getSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
    }

}
