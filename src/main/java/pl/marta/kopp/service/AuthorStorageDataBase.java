package pl.marta.kopp.service;

import pl.marta.kopp.connector.DBConnector;
import org.hibernate.Query;
import org.hibernate.Session;
import pl.marta.kopp.domain.Author;


public class AuthorStorageDataBase implements AuthorStorage {
    private DBConnector dbConnector;

    public AuthorStorageDataBase(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public void add(Author author) {
        Session session = dbConnector.getSession();

        if (!isExists(author)) {
        session.getTransaction().begin();
        session.save(author);
        session.getTransaction().commit();
    }
    }

    @Override
    public boolean isExists(Author author) {
        Session session = dbConnector.getSession();
        Query query = session.createQuery("from Author where authorName=:name AND authorSurname=:surname ");
        query.setParameter("name", author.getAuthorName());
        query.setParameter("surname", author.getAuthorSurname());
        return !(query.list().isEmpty());
    }
}
