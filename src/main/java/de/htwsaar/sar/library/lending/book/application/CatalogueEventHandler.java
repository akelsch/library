package de.htwsaar.sar.library.lending.book.application;

import de.htwsaar.sar.library.catalogue.domain.BookInstanceAddedToCatalogueEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CatalogueEventHandler {

    @EventListener
    public void handleBookInstanceAddedToCatalogueEvent(BookInstanceAddedToCatalogueEvent event) {
        System.out.println(event.getBookInstance());
    }
}
