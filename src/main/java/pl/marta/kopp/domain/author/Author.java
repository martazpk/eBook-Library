package pl.marta.kopp.domain.author;

import lombok.Getter;
import pl.marta.kopp.domain.book.Book;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Author {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    private Author() {
    }
    @Override
    public String toString(){
        return this.name+" "+this.surname;
    }


}