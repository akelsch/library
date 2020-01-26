package de.htwsaar.sar.library.lending.student.domain;

import de.htwsaar.sar.library.lending.book.domain.BookDatabaseEntity;
import de.htwsaar.sar.library.lending.book.domain.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final BookService bookService;

    public Iterable<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void checkoutBook(Long studentNumber, BookDatabaseEntity bookDatabaseEntity) {
        Optional<Student> s = studentRepository.findById(studentNumber);
        Optional<BookDatabaseEntity> b = bookService.findBookDatabaseEntityByBookId(bookDatabaseEntity.getBookId());

        if (s.isPresent() && b.isPresent()) {
            // TODO check if book is available
            Student student = s.get();
            BookDatabaseEntity book = b.get();

            student.addCheckout(book);
            studentRepository.save(student);
        }
    }
}
