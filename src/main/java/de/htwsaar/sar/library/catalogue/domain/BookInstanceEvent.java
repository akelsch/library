package de.htwsaar.sar.library.catalogue.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public interface BookInstanceEvent {

    BookInstance getBookInstance();

    @Getter
    class BookInstanceAdded extends ApplicationEvent implements BookInstanceEvent {

        private final BookInstance bookInstance;

        public BookInstanceAdded(Object source, BookInstance bookInstance) {
            super(source);
            this.bookInstance = bookInstance;
        }
    }
}
