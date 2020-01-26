package de.htwsaar.sar.library.catalogue.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CatalogueService {

    private final BookRepository bookRepository;
    private final BookInstanceRepository bookInstanceRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<BookInstance> findAllBookInstancesByIsbn(String isbn) {
        return bookInstanceRepository.findAllByIsbn(isbn);
    }

    public BookInstance saveBookInstance(BookInstance bookInstance) {
        Optional<Book> book = bookRepository.findById(bookInstance.getIsbn());
        if (book.isEmpty()) {
            throw new IllegalStateException("Creating book instance for non-existing book!");
        }

        bookInstanceRepository.save(bookInstance);
        applicationEventPublisher.publishEvent(new BookInstanceAddedToCatalogueEvent(this, bookInstance));
        return bookInstance;
    }
}
