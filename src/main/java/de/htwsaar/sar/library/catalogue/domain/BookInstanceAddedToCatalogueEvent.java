package de.htwsaar.sar.library.catalogue.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BookInstanceAddedToCatalogueEvent extends ApplicationEvent {

    private BookInstance bookInstance;

    public BookInstanceAddedToCatalogueEvent(Object source, BookInstance bookInstance) {
        super(source);
        this.bookInstance = bookInstance;
    }
}
