package de.htwsaar.sar.library.catalogue.application;

import de.htwsaar.sar.library.catalogue.domain.Book;
import de.htwsaar.sar.library.catalogue.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Iterable<Book> displayCatalogue() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book addBookToCatalogue(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}
