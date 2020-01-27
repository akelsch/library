package de.htwsaar.sar.library.lending.book.infrastructure;

import de.htwsaar.sar.library.lending.book.domain.AvailableBook;
import de.htwsaar.sar.library.lending.book.domain.Book;
import de.htwsaar.sar.library.lending.book.domain.BookState;
import de.htwsaar.sar.library.lending.book.domain.CheckedOutBook;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class BookDatabaseEntity {

    @Id
    @GeneratedValue
    private Long rowNumber;

    private UUID bookId;

    private BookState bookState;

    private Long checkedOutByStudent;

    public Book toDomainModel() {
        return switch (bookState) {
            case AVAILABLE -> toAvailableBook();
            case CHECKED_OUT -> toCheckedOutBook();
        };
    }

    private AvailableBook toAvailableBook() {
        return new AvailableBook(bookId);
    }

    private CheckedOutBook toCheckedOutBook() {
        return new CheckedOutBook(bookId, checkedOutByStudent);
    }
}
