package de.htwsaar.sar.library.catalogue.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface BookInstanceRepository extends CrudRepository<BookInstance, UUID> {

    Iterable<BookInstance> findAllByIsbn(String isbn);
}
