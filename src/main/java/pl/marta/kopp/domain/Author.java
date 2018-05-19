package pl.marta.kopp.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Author {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String authorName;
    @Column
    private String authorSurname;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(String authorName, String authorSurname) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public Author() {
    }

    public long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public List<Book> getBooks() {
        return books;
    }
}