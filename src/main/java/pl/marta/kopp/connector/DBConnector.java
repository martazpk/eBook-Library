package pl.marta.kopp.connector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnector {
    private static Session session;
    private static DBConnector dbConnector = null;
    private SessionFactory sessionFactory;

    private DBConnector() {
        Configuration conf = new Configuration();

        sessionFactory = conf.configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static DBConnector getInstance() {
        if (dbConnector == null) {
            dbConnector = new DBConnector();
        }
        return dbConnector;
    }

    public Session getSession() {
        return session;
    }

    public void close() {
        session.close();
        sessionFactory.close();
    }
}
