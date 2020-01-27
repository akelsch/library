package de.htwsaar.sar.library.lending.student.domain;

import de.htwsaar.sar.library.lending.book.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final BookService bookService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public Iterable<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void checkoutBook(Long studentNumber, UUID bookId) {
        Optional<Student> s = studentRepository.findById(studentNumber);
        Optional<BookDatabaseEntity> b = bookService.findBookDatabaseEntityByBookId(bookId);

        if (s.isPresent() && b.isPresent()) {
            Student student = s.get();
            BookDatabaseEntity book = b.get();

            if (book.toDomainModel() instanceof AvailableBook) {
                applicationEventPublisher.publishEvent(new StudentEvent.BookCheckedOut(this, student.getStudentNumber(), book));
            } else {
                throw new IllegalStateException("Checking out an unavailable book!");
            }
        }
    }
}
