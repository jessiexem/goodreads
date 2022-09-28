package vttp2.goodreadsbackend.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class BookDetails {
    
    private String bookId;
    private String title;
    private String authors;
    private String description;
    private double rating;
    private String genres;
    private String image_url;

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
    public String getAuthors() {
        return authors;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public static BookDetails create (SqlRowSet rs) {

        BookDetails bookDetails = new BookDetails();
        bookDetails.setBookId(rs.getString("book_id"));
        bookDetails.setAuthors(rs.getString("authors"));
        bookDetails.setDescription(rs.getString("description"));
        bookDetails.setGenres(rs.getString("genres"));
        bookDetails.setTitle(rs.getString("title"));
        bookDetails.setRating(rs.getDouble("rating"));
        bookDetails.setImage_url(rs.getString("image_url"));

        return bookDetails;
    }


    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("bookId", bookId)
        .add ("title", title)
        .add("authors", authors)
        .add("description", description)
        .add("genres", genres)
        .add("rating", rating)
        .add("imageUrl", image_url)
        .build();
    }
}
