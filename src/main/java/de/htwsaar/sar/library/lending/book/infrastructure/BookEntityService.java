package de.htwsaar.sar.library.lending.book.infrastructure;

import de.htwsaar.sar.library.catalogue.domain.BookInstance;
import de.htwsaar.sar.library.lending.book.domain.BookState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookEntityService {

    private final BookEntityRepository bookEntityRepository;

    public void saveNewBookInstance(BookInstance bookInstance) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(bookInstance.getBookId());
        bookEntity.setBookState(BookState.AVAILABLE);
        bookEntityRepository.save(bookEntity);
    }

    public Optional<BookEntity> findBookEntityByBookId(UUID bookId) {
        return bookEntityRepository.findByBookId(bookId);
    }

    public void updateBookEntity(BookEntity bookEntity) {
        bookEntityRepository.save(bookEntity);
    }
}
