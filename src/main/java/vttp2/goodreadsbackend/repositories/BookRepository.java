package vttp2.goodreadsbackend.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2.goodreadsbackend.models.BookDetails;
import vttp2.goodreadsbackend.models.BookSummary;

@Repository
public class BookRepository {

    private static final String SQL_GET_BOOKS = "select book_id, title from goodreads.book2018 order by title asc limit ? offset ?;";
    private static final String SQL_GET_BOOK_BY_ID = "select book_id, title, authors, description, rating, genres, image_url from goodreads.book2018 where book_id = ?;";

    @Autowired
    private JdbcTemplate template;

    private final Logger logger = Logger.getLogger(BookRepository.class.getName());

    public List<BookSummary> getBooks(Integer limit, Integer offset) {

        SqlRowSet rs = template.queryForRowSet(SQL_GET_BOOKS,limit,offset);

        List<BookSummary> summaries = new LinkedList<>();
        
        while (rs.next()) {

            BookSummary summary = BookSummary.create(rs);
            summaries.add(summary);
        }
        return summaries;
    }

    public Optional<BookDetails> getBookById (String bookId) {
        SqlRowSet rs = template.queryForRowSet(SQL_GET_BOOK_BY_ID, bookId);

        if (!rs.first()) {
            logger.warning(">>>> BookRepository: getBookById: no data found");
            return Optional.empty();
        }
        else return Optional.of(BookDetails.create(rs));
    }

    
}
