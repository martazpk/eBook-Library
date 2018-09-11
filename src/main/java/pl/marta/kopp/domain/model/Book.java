package pl.marta.kopp.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String isbn;
    private String title;
    private boolean borrow;
    @JoinColumn
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Author> authors;


    public Book(String isbn,String title, List<Author> authors) {
        this.isbn=isbn;
        this.title = title;
        this.authors = authors;
        this.borrow = false;
    }

    private Book() {
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", borrow=" + borrow +
                ", authors=" + authors +
                '}';
    }
}
