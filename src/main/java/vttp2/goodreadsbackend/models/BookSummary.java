package vttp2.goodreadsbackend.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class BookSummary {
    private String bookId;
    private String title;

    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public static BookSummary create (SqlRowSet rs) {
        BookSummary b = new BookSummary();
        b.setTitle(rs.getString("title"));
        b.setBookId(rs.getString("book_id"));
        return b;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("bookId",bookId)
        .add("title", title)
        .build();
    }

}
