package pl.marta.kopp;


import pl.marta.kopp.CreateDataBase.CreateBookStorage;
import pl.marta.kopp.domain.author.Author;
import pl.marta.kopp.domain.book.Book;
import pl.marta.kopp.persistence.*;
import pl.marta.kopp.view.CliSystemInterface;
import pl.marta.kopp.view.EbookView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
BookStorage bookStorage=new BookStorageJpa();
BorrowStorage borrowStorage=new BorrowStorageJpa();

       CreateBookStorage.create(bookStorage);

new EbookView(new CliSystemInterface(new Scanner(System.in)),new UserStorageJpa(),bookStorage,borrowStorage).show();


}}
