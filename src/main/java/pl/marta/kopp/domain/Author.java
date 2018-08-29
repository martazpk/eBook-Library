package pl.marta.kopp.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Author {
    @Id
    @GeneratedValue
    private long id;
    private String authorName;
    private String authorSurname;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(String authorName, String authorSurname) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public Author() {
    }

}