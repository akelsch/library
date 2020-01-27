package de.htwsaar.sar.library.lending.book.infrastructure;

import de.htwsaar.sar.library.catalogue.domain.BookInstance;
import de.htwsaar.sar.library.lending.book.domain.BookState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookDatabaseEntityRepository bookDatabaseEntityRepository;

    public void saveNewBookInstance(BookInstance bookInstance) {
        BookDatabaseEntity bookDatabaseEntity = new BookDatabaseEntity();
        bookDatabaseEntity.setBookId(bookInstance.getBookId());
        bookDatabaseEntity.setBookState(BookState.AVAILABLE);
        bookDatabaseEntityRepository.save(bookDatabaseEntity);
    }

    public Optional<BookDatabaseEntity> findBookDatabaseEntityByBookId(UUID bookId) {
        return bookDatabaseEntityRepository.findByBookId(bookId);
    }

    public void updateBookDatabaseEntity(BookDatabaseEntity bookDatabaseEntity) {
        bookDatabaseEntityRepository.save(bookDatabaseEntity);
    }
}
