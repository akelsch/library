package de.htwsaar.sar.library.lending.student.application;

import de.htwsaar.sar.library.lending.book.domain.BookDatabaseEntity;
import de.htwsaar.sar.library.lending.book.domain.BookService;
import de.htwsaar.sar.library.lending.book.domain.BookState;
import de.htwsaar.sar.library.lending.student.domain.StudentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StudentEventHandler {

    private final BookService bookService;

    @EventListener
    public void handleBookInstanceAddedToCatalogueEvent(StudentEvent.BookCheckedOut event) {
        Long studentNumber = event.getStudentNumber();
        BookDatabaseEntity book = event.getBook();

        log.info("Received new BookCheckedOut for Student {} and Book {}", studentNumber, book.getBookId());

        book.setBookState(BookState.CHECKED_OUT);
        book.setCheckedOutByStudent(studentNumber);
        bookService.updateBookDatabaseEntity(book);
    }
}
