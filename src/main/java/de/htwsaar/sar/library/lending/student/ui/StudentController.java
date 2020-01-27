package de.htwsaar.sar.library.lending.student.ui;

import de.htwsaar.sar.library.lending.book.infrastructure.BookEntity;
import de.htwsaar.sar.library.lending.student.application.StudentService;
import de.htwsaar.sar.library.lending.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Iterable<Student> listAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/{studentNumber}/checkout/{bookId}")
    public void checkoutBook(@PathVariable Long studentNumber, @PathVariable UUID bookId) {
        studentService.checkoutBook(studentNumber, bookId);
    }

    @PostMapping("/{studentNumber}/return")
    public void returnBook(@PathVariable String studentNumber, @RequestBody BookEntity bookEntity) {
        // find book
        // check if book is checked-out by student
        // return
    }
}
