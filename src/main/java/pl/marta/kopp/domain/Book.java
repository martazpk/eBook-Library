package pl.marta.kopp.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String title;
    private boolean borrow;
    @JoinColumn
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors;


    public Book(String title, List<Author> authors, boolean borrow) {
        this.title = title;
        this.authors = authors;
        this.borrow = borrow;
    }

    private Book() {
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
