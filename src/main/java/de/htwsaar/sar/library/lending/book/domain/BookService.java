package de.htwsaar.sar.library.lending.book.domain;

import de.htwsaar.sar.library.catalogue.domain.BookInstance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookDatabaseEntityRepository bookDatabaseEntityRepository;

    public void saveBookInstance(BookInstance bookInstance) {
        BookDatabaseEntity bookDatabaseEntity = new BookDatabaseEntity();
        bookDatabaseEntity.setBookId(bookInstance.getId());
        bookDatabaseEntity.setBookState(BookState.AVAILABLE);
        bookDatabaseEntityRepository.save(bookDatabaseEntity);
    }

    public Optional<BookDatabaseEntity> findBookDatabaseEntityByBookId(UUID bookId) {
        return bookDatabaseEntityRepository.findByBookId(bookId);
    }
}
