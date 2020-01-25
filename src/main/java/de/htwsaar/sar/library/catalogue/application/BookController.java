package de.htwsaar.sar.library.catalogue.application;

import de.htwsaar.sar.library.catalogue.domain.Book;
import de.htwsaar.sar.library.catalogue.domain.CatalogueService;
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
