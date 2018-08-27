package pl.marta.kopp;

import pl.marta.kopp.CreateDataBase.CreateBookStorage;
import pl.marta.kopp.connector.DBConnector;
import pl.marta.kopp.service.*;
import pl.marta.kopp.view.CliSystemInterface;
import pl.marta.kopp.view.EbookView;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        DBConnector conn = DBConnector.getInstance();

        BookStorage bookStorage = new BookStorageDataBase(conn);
        AuthorStorage authorStorage = new AuthorStorageDataBase(conn);

        CreateBookStorage.create(bookStorage, authorStorage);
new EbookView(new CliSystemInterface(new Scanner(System.in)),new UserStorageDataBase(conn)).show();

}}
