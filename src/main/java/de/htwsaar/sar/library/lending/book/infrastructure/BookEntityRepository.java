package de.htwsaar.sar.library.lending.book.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookEntityRepository extends CrudRepository<BookEntity, UUID> {

    Optional<BookEntity> findByBookId(UUID bookId);
}
