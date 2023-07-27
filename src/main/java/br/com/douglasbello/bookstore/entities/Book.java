package br.com.douglasbello.bookstore.entities;

import br.com.douglasbello.bookstore.entities.enums.BookStatus;
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
    @Column(columnDefinition = "TEXT")
    private String overview;
    private Integer publishedYear;
    private Double salePrice;
    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;
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

    public Book(String title, String overview, Integer publishedYear, Double salePrice, BookStatus status) {
        this.title = title;
        this.overview = overview;
        this.publishedYear = publishedYear;
        this.salePrice = salePrice;
        this.status = status;
    }

    public Book(String title, String overview, Integer publishedYear, Double salePrice) {
        this.title = title;
        this.overview = overview;
        this.publishedYear = publishedYear;
        this.salePrice = salePrice;
    }

    public Book(String title, String overview, Integer publishedYear, Double salePrice, BookStatus status, Author author, Rent rentedBook) {
        this.title = title;
        this.overview = overview;
        this.publishedYear = publishedYear;
        this.salePrice = salePrice;
        this.status = status;
        this.author = author;
        this.rentedBook = rentedBook;
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

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
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