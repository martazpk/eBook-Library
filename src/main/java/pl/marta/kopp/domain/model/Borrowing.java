package pl.marta.kopp.domain.model;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
@Getter
public class Borrowing {
    @Id
    @GeneratedValue
    private long id;
    private long bookId;
    private long userId;
    private Calendar borrowDate;

    private Borrowing(Builder builder) {
        this.bookId = builder.bookId;
        this.userId = builder.userId;
        this.borrowDate = Calendar.getInstance();
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

