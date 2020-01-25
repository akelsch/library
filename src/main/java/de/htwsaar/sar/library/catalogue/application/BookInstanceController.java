package de.htwsaar.sar.library.catalogue.application;

import de.htwsaar.sar.library.catalogue.domain.BookInstance;
import de.htwsaar.sar.library.catalogue.domain.CatalogueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book-instances")
public class BookInstanceController {

    public final CatalogueService catalogueService;

    @GetMapping
    public Iterable<BookInstance> listAllBookInstances(String isbn) {
        return catalogueService.findAllBookInstancesByIsbn(isbn);
    }

    @PostMapping
    public BookInstance addSingleBookInstance(@RequestBody BookInstance bookInstance) {
        return catalogueService.saveBookInstance(bookInstance);
    }
}
