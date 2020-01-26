package de.htwsaar.sar.library.lending.book.application;

import de.htwsaar.sar.library.catalogue.domain.BookInstanceAddedToCatalogueEvent;
import de.htwsaar.sar.library.lending.book.domain.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CatalogueEventHandler {

    private final BookService bookService;

    @EventListener
    public void handleBookInstanceAddedToCatalogueEvent(BookInstanceAddedToCatalogueEvent event) {
        bookService.saveBookInstance(event.getBookInstance());
    }
}
