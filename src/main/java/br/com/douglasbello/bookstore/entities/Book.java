package br.com.douglasbello.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String overview;
    private Double salePrice;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @JsonIgnore
    @OneToOne(mappedBy = "book")
    private Rent rentedBook;
    @JsonIgnore
    @ManyToMany(mappedBy = "boughtBooks")
    private Set<Customer> customersBoughtBooks = new HashSet<>();

    public Book(){}

    public Book(String title, Author author, String overview, Double salePrice) {
        this.title = title;
        this.author = author;
        this.overview = overview;
        this.salePrice = salePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Rent getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(Rent rentedBook) {
        this.rentedBook = rentedBook;
    }

    public Set<Customer> getCustomersBoughtBooks() {
        return customersBoughtBooks;
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
                ", overview='" + overview + '\'' +
                ", author=" + author +
                ", rentedBook=" + rentedBook +
                ", customersBoughtBooks=" + customersBoughtBooks +
                '}';
    }
}