package de.htwsaar.sar.library.lending.book.application;

import de.htwsaar.sar.library.catalogue.domain.BookInstance;
import de.htwsaar.sar.library.catalogue.domain.BookInstanceAddedToCatalogueEvent;
import de.htwsaar.sar.library.lending.book.domain.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CatalogueEventHandler {

    private final BookService bookService;

    @EventListener
    public void handleBookInstanceAddedToCatalogueEvent(BookInstanceAddedToCatalogueEvent event) {
        BookInstance bookInstance = event.getBookInstance();
        log.info("Received new BookInstanceAddedToCatalogueEvent with ID {}", bookInstance.getBookId());
        bookService.saveNewBookInstance(bookInstance);
    }
}
