package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    private long id;
    private String title;
    private List<Author> author = new ArrayList<>();
    private String overview;

    public Book(){}

    public Book(long id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
    }

    public Book(long id, String title, List<Author> author, String overview) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.overview = overview;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Author> getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", overview='" + overview + '\'' +
                '}';
    }
}