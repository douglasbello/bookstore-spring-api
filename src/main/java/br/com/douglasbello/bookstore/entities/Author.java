package entities;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    List<Book> publishedBooks = new ArrayList<>();

    public Author(){}

    public Author(long id, String firstName, String lastName, List<Book> publishedBooks)
    {
        super(id, firstName, lastName);
        this.publishedBooks = publishedBooks;
    }

    public List<Book> getBooks()
    {
        return publishedBooks;
    }

    @Override
    public String toString()
    {
        return "Author{" +
                "books=" + publishedBooks +
                "} " + super.toString();
    }
}