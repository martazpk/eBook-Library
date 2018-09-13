package pl.marta.kopp.domain.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
public class Borrowing {
    @Id
    @GeneratedValue
    private long id;
    private long bookId;
    private long userId;
    @Temporal(TemporalType.DATE)
    private Calendar dateOfBorrow;
    @Temporal(TemporalType.DATE)
    private Calendar dateOfReturn;

    private Borrowing(Builder builder) {
        this.bookId = builder.bookId;
        this.userId = builder.userId;
        this.dateOfBorrow = Calendar.getInstance();
    }

    private Borrowing() {
    }


    public static class Builder {
        private long bookId;
        private long userId;

        public Builder() {
        }

        public Builder bookId(final long bookId) {
            this.bookId = bookId;
            return this;
        }
        public Builder userId(final long userId) {
            this.userId = userId;
            return this;
        }

        public Borrowing build() {
            return new Borrowing(this);
        }
    }
}

