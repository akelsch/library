package de.htwsaar.sar.library.lending.student.application;

import de.htwsaar.sar.library.lending.book.application.BookEntityService;
import de.htwsaar.sar.library.lending.book.domain.AvailableBook;
import de.htwsaar.sar.library.lending.book.domain.Book;
import de.htwsaar.sar.library.lending.book.domain.CheckedOutBook;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntity;
import de.htwsaar.sar.library.lending.student.domain.Student;
import de.htwsaar.sar.library.lending.student.infrastructure.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final BookEntityService bookEntityService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public Iterable<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Book> findAllCheckedOutBooksForStudent(Long studentNumber) {
        return bookEntityService.findAllCheckedOutBooks().stream()
                .filter(b -> b.getCheckedOutByStudent().equals(studentNumber))
                .map(BookEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    public void checkoutBook(Long studentNumber, UUID bookId) {
        Optional<Student> student = studentRepository.findById(studentNumber);
        if (student.isEmpty()) {
            throw new IllegalStateException("Checking out with a non-existing student!");
        }

        Optional<BookEntity> bookEntity = bookEntityService.findBookEntityByBookId(bookId);
        if (bookEntity.isEmpty()) {
            throw new IllegalStateException("Checking out a non-existing book!");
        }

        Book book = bookEntity.map(BookEntity::toDomainModel).get();
        if (!(book instanceof AvailableBook)) {
            throw new IllegalStateException("Checking out an unavailable book!");
        }

        applicationEventPublisher.publishEvent(new StudentEvent.BookCheckedOut(this, studentNumber, bookEntity.get()));
    }

    public void returnBook(Long studentNumber, UUID bookId) {
        Optional<Student> student = studentRepository.findById(studentNumber);
        if (student.isEmpty()) {
            throw new IllegalStateException("Returning with a non-existing student!");
        }

        Optional<BookEntity> bookEntity = bookEntityService.findBookEntityByBookId(bookId);
        if (bookEntity.isEmpty()) {
            throw new IllegalStateException("Returning a non-existing book!");
        }

        Book book = bookEntity.map(BookEntity::toDomainModel).get();
        if (!(book instanceof CheckedOutBook)) {
            throw new IllegalStateException("Returning a book that is not checked out!");
        }

        CheckedOutBook checkedOutBook = (CheckedOutBook) book;
        if (studentNumber.equals(checkedOutBook.getStudentNumber())) {
            applicationEventPublisher.publishEvent(new StudentEvent.BookReturned(this, studentNumber, bookEntity.get()));
        }
    }
}
