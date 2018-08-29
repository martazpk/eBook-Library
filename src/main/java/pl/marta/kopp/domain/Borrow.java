package pl.marta.kopp.domain;


import lombok.Setter;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;


@Entity
@Getter
@Setter
public class Borrow {
    @Id
    @GeneratedValue
    private long id;
    private long bookId;
    private long userId;
    private Date date;
}
