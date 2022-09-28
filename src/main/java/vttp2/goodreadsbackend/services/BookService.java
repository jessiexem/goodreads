package vttp2.goodreadsbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2.goodreadsbackend.repositories.BookRepository;
import vttp2.goodreadsbackend.models.BookDetails;
import vttp2.goodreadsbackend.models.BookSummary;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;

    public List<BookSummary> search (Integer limit, Integer offset) {
        return bookRepo.getBooks(limit, offset);
    }

    public Optional<BookDetails> getBookById (String bookId) {
        return bookRepo.getBookById(bookId);
    }
}
