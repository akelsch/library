package de.htwsaar.sar.library.lending.book.application;

import de.htwsaar.sar.library.catalogue.domain.BookInstance;
import de.htwsaar.sar.library.catalogue.domain.CatalogueEvent;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntity;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CatalogueEventHandler {

    private final BookEntityService bookEntityService;

    @EventListener
    public void handleBookInstanceAddedEvent(CatalogueEvent.BookInstanceAdded event) {
        BookInstance bookInstance = event.getBookInstance();

        log.info("Received new BookInstanceAdded event for Book {}", bookInstance.getBookId());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(bookInstance.getBookId());
        bookEntity.setBookState(BookEntity.BookState.AVAILABLE);
        bookEntityService.saveBookEntity(bookEntity);
    }
}
