package pl.marta.kopp.service;

import org.hibernate.Query;
import pl.marta.kopp.connector.DBConnector;
import org.hibernate.Session;
import pl.marta.kopp.domain.Book;
import java.util.List;


public class BookStorageDataBase implements BookStorage {
        private DBConnector dbConnector;

    public BookStorageDataBase(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void add(Book book) {
            Session session = dbConnector.getSession();
            session.getTransaction().begin();
            session.save(book);
            session.getTransaction().commit();
        }

    @Override
    public List showAll() {
        Session session = dbConnector.getSession();
        Query query = session.createQuery("from Book ");
        return query.list();
    }
}