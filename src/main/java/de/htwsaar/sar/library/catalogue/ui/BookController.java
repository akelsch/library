package de.htwsaar.sar.library.catalogue.ui;

import de.htwsaar.sar.library.catalogue.application.CatalogueService;
import de.htwsaar.sar.library.catalogue.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final CatalogueService catalogueService;

    @GetMapping
    public Iterable<Book> listAllBooks() {
        return catalogueService.findAllBooks();
    }

    @PostMapping
    public Book addSingleBook(@RequestBody Book book) {
        return catalogueService.saveBook(book);
    }
}
