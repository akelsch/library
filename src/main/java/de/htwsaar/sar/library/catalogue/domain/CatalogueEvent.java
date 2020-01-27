package de.htwsaar.sar.library.catalogue.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public interface CatalogueEvent {

    @Getter
    class BookInstanceAdded extends ApplicationEvent implements CatalogueEvent {

        private final BookInstance bookInstance;

        public BookInstanceAdded(Object source, BookInstance bookInstance) {
            super(source);
            this.bookInstance = bookInstance;
        }
    }
}
