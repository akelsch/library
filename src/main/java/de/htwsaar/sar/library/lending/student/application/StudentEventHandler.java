package de.htwsaar.sar.library.lending.student.application;

import de.htwsaar.sar.library.lending.book.application.BookEntityService;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StudentEventHandler {

    private final BookEntityService bookEntityService;

    @EventListener
    public void handleBookCheckedOutEvent(StudentEvent.BookCheckedOut event) {
        Long studentNumber = event.getStudentNumber();
        BookEntity bookEntity = event.getBookEntity();

        log.info("Received new BookCheckedOut event for Student {} and Book {}", studentNumber, bookEntity.getBookId());

        bookEntity.setBookState(BookEntity.BookState.CHECKED_OUT);
        bookEntity.setCheckedOutByStudent(studentNumber);
        bookEntityService.saveBookEntity(bookEntity);
    }

    @EventListener
    public void handleBookReturnedEvent(StudentEvent.BookReturned event) {
        Long studentNumber = event.getStudentNumber();
        BookEntity bookEntity = event.getBookEntity();

        log.info("Received new BookReturned event for Student {} and Book {}", studentNumber, bookEntity.getBookId());

        bookEntity.setBookState(BookEntity.BookState.AVAILABLE);
        bookEntity.setCheckedOutByStudent(null);
        bookEntityService.saveBookEntity(bookEntity);
    }
}
