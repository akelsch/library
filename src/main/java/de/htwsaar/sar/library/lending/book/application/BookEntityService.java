package de.htwsaar.sar.library.lending.book.application;

import de.htwsaar.sar.library.lending.book.infrastructure.BookEntity;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookEntityService {

    private final BookEntityRepository bookEntityRepository;

    public void saveBookEntity(BookEntity bookEntity) {
        bookEntityRepository.save(bookEntity);
    }

    public Optional<BookEntity> findBookEntityByBookId(UUID bookId) {
        return bookEntityRepository.findByBookId(bookId);
    }
}
