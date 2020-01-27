package de.htwsaar.sar.library.lending.student.application;

import de.htwsaar.sar.library.lending.book.domain.BookState;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntity;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntityService;
import de.htwsaar.sar.library.lending.student.domain.StudentEvent;
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
        BookEntity book = event.getBook();

        log.info("Received new BookCheckedOut event for Student {} and Book {}", studentNumber, book.getBookId());

        book.setBookState(BookState.CHECKED_OUT);
        book.setCheckedOutByStudent(studentNumber);
        bookEntityService.updateBookEntity(book);
    }
}
