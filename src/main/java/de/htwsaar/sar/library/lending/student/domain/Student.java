package de.htwsaar.sar.library.lending.student.domain;

import de.htwsaar.sar.library.lending.book.domain.BookDatabaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(generator = "student_sequence")
    @SequenceGenerator(name = "student_sequence", initialValue = 4711)
    private Long studentNumber;

    private String name;

    @OneToMany
    private List<BookDatabaseEntity> checkouts;

    public void addCheckout(BookDatabaseEntity book) {
        checkouts.add(book);
    }
}
