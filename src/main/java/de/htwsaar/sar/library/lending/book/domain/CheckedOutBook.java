package de.htwsaar.sar.library.lending.book.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class CheckedOutBook implements Book {

    private final UUID bookId;

    private final Long student;
}
