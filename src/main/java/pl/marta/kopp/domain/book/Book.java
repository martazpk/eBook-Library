package pl.marta.kopp.domain.book;

import lombok.Getter;
import pl.marta.kopp.domain.author.Author;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    @Column
    private String title;
    private boolean borrow;
    @JoinColumn
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Author> authors;


    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
        this.borrow = false;
    }

    private Book() {
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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
