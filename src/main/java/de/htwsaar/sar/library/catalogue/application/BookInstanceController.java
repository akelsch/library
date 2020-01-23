package de.htwsaar.sar.library.catalogue.application;

import de.htwsaar.sar.library.catalogue.domain.BookInstance;
import de.htwsaar.sar.library.catalogue.domain.BookInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-instances")
public class BookInstanceController {

    public final BookInstanceRepository bookInstanceRepository;

    @Autowired
    public BookInstanceController(BookInstanceRepository bookInstanceRepository) {
        this.bookInstanceRepository = bookInstanceRepository;
    }

    @GetMapping
    public Iterable<BookInstance> displayAllBookInstancesForIsbn(String isbn) {
        return bookInstanceRepository.findAllByIsbn(isbn);
    }

    @PostMapping
    public BookInstance addBookInstanceToCatalogue(@RequestBody BookInstance bookInstance) {
        return bookInstanceRepository.save(bookInstance);
    }
}
