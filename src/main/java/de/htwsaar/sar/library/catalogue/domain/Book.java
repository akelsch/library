package de.htwsaar.sar.library.catalogue.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Book {

    @Id
    private String isbn;

    private String author;

    private String title;
}
