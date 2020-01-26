package de.htwsaar.sar.library.lending.book.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class BookDatabaseEntity {

    @Id
    private UUID id;

    private BookState state;

    private UUID checkOutByStudent;

    public Book toDomainModel() {
        return switch (state) {
            case AVAILABLE -> toAvailableBook();
            case CHECKED_OUT -> toCheckedOutBook();
        };
    }

    private AvailableBook toAvailableBook() {
        return new AvailableBook(id);
    }

    private CheckedOutBook toCheckedOutBook() {
        return new CheckedOutBook(id, checkOutByStudent);
    }

    private enum BookState {
        AVAILABLE, CHECKED_OUT
    }
}
