package de.htwsaar.sar.library.lending.student.domain;

import de.htwsaar.sar.library.lending.book.infrastructure.BookEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public interface StudentEvent {

    Long getStudentNumber();

    @Getter
    class BookCheckedOut extends ApplicationEvent implements StudentEvent {

        private final Long studentNumber;
        private final BookEntity bookEntity;

        public BookCheckedOut(Object source, Long studentNumber, BookEntity bookEntity) {
            super(source);
            this.studentNumber = studentNumber;
            this.bookEntity = bookEntity;
        }
    }
}
