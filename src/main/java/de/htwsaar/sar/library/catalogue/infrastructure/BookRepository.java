package de.htwsaar.sar.library.catalogue.infrastructure;

import de.htwsaar.sar.library.catalogue.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
