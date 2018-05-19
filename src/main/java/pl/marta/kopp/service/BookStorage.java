package pl.marta.kopp.service;

import pl.marta.kopp.domain.Book;

import java.util.List;

public interface BookStorage {
    void add(Book book);
    List showAll();
}
