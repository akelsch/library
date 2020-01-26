package de.htwsaar.sar.library.lending.student.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(generator = "student_sequence")
    @SequenceGenerator(name = "student_sequence", initialValue = 4711)
    private Long studentNumber;

    private String name;

    // private List<UUID> checkouts;
}
