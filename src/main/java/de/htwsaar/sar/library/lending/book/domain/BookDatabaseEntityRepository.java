package de.htwsaar.sar.library.lending.book.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface BookDatabaseEntityRepository extends CrudRepository<BookDatabaseEntity, UUID> {
}