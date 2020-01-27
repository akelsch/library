package de.htwsaar.sar.library.catalogue.infrastructure;

import de.htwsaar.sar.library.catalogue.domain.BookInstance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookInstanceRepository extends CrudRepository<BookInstance, UUID> {

    Iterable<BookInstance> findAllByIsbn(String isbn);
}
