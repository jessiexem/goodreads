package vttp2.goodreadsbackend.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2.goodreadsbackend.models.BookDetails;
import vttp2.goodreadsbackend.models.BookSummary;
import vttp2.goodreadsbackend.services.BookService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksRestController {
    
    @Autowired
    BookService bookSvc;

    @GetMapping (path = "/api/books")
    public ResponseEntity<String> searchBooks(
        @RequestParam (name= "limit", defaultValue = "20") Integer limit,
        @RequestParam (name = "offset", defaultValue = "0") Integer offset) {

        List<BookSummary> summaries = bookSvc.search(limit, offset);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (BookSummary summary : summaries) {
            arrBuilder.add(summary.toJson());
        }        
        
        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    @GetMapping (path = "/api/books/{bookId}")
    public ResponseEntity<String> getBookByBookId(@PathVariable (name="bookId") String bookId) {

        Optional<BookDetails> opt = bookSvc.getBookById(bookId);
        
        if (opt.isEmpty()) {
            return ResponseEntity.status(404)
            .body("Book with bookId: %s not found".formatted(bookId));
        } else {

            BookDetails details = opt.get();
            return ResponseEntity.ok(details.toJson().toString());
        }
    } 
}
