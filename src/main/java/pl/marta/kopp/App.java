package pl.marta.kopp;

import pl.marta.kopp.CreateDataBase.CreateBookStorage;
import pl.marta.kopp.service.*;
import pl.marta.kopp.view.CliSystemInterface;
import pl.marta.kopp.view.EbookView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("eBookPersistence");
        EntityManager entityManager = factory.createEntityManager();

        BookStorage bookStorage = new BookStorageJpa();
        AuthorStorage authorStorage = new AuthorStorageJpa();

//        CreateBookStorage.create(bookStorage, authorStorage);
//new EbookView(new CliSystemInterface(new Scanner(System.in)),new UserStorageJpa(conn)).show();

}}
