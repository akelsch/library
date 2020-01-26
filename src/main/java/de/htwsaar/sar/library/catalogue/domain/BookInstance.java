package de.htwsaar.sar.library.catalogue.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class BookInstance {

    @Id
    @GeneratedValue
    private UUID bookId;

    private String isbn;
}
