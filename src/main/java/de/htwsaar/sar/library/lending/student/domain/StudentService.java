package de.htwsaar.sar.library.lending.student.domain;

import de.htwsaar.sar.library.lending.book.domain.AvailableBook;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntity;
import de.htwsaar.sar.library.lending.book.infrastructure.BookEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    public void checkoutBook(Long studentNumber, UUID bookId) {
        Optional<Student> s = studentRepository.findById(studentNumber);
        Optional<BookEntity> b = bookEntityService.findBookEntityByBookId(bookId);

        if (s.isPresent() && b.isPresent()) {
            Student student = s.get();
            BookEntity bookEntity = b.get();

            if (bookEntity.toDomainModel() instanceof AvailableBook) {
                applicationEventPublisher.publishEvent(new StudentEvent.BookCheckedOut(this, student.getStudentNumber(), bookEntity));
            } else {
                throw new IllegalStateException("Checking out an unavailable book!");
            }
        }
    }
}
