package de.htwsaar.sar.library.lending.student.application;

import de.htwsaar.sar.library.lending.book.domain.BookDatabaseEntity;
import de.htwsaar.sar.library.lending.book.domain.BookService;
import de.htwsaar.sar.library.lending.student.domain.Student;
import de.htwsaar.sar.library.lending.student.domain.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final BookService bookService;

    @GetMapping
    public Iterable<Student> listAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/{studentNumber}/checkout")
    public void checkoutBook(@PathVariable String studentNumber, @RequestBody BookDatabaseEntity bookDatabaseEntity) {
        // find book
        // check if available
        // checkout
    }

    @PostMapping("/{studentNumber}/return")
    public void returnBook(@PathVariable String studentNumber, @RequestBody BookDatabaseEntity bookDatabaseEntity) {
        // find book
        // check if book is checked-out by student
        // return
    }
}
