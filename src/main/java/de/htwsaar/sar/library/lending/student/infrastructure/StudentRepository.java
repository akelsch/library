package de.htwsaar.sar.library.lending.student.infrastructure;

import de.htwsaar.sar.library.lending.student.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
