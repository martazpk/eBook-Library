package pl.marta.kopp.domain.boorow;


import lombok.Setter;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.sql.Date;
import java.util.Calendar;


@Entity
@Getter
public class Borrow {
    @Id
    @GeneratedValue
    private long id;
    private long bookId;
    private long userId;
    private Calendar date;

    public Borrow(long bookId, long userId) {
        this.bookId = bookId;
        this.userId = userId;
        this.date=Calendar.getInstance();
    }

    private Borrow() {
    }
}
