package de.htwsaar.sar.library.lending.book.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class AvailableBook implements Book {

    private UUID id;
}
