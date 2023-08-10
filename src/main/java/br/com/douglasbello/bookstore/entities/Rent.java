package br.com.douglasbello.bookstore.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "rent" )
public class Rent {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;
    private Double price;
    private LocalDate rentedAt;
    private LocalDate returnDate;
    @OneToOne
    @JoinColumn( name = "book_id" )
    private Book book;
    @OneToOne
    @JoinColumn( name = "customer_id" )
    private Customer customer;

    public Rent() {
    }

    public Rent(Double price, Book book, Customer customer) {
        this.price = price;
        this.rentedAt = LocalDate.now();
        this.returnDate = rentedAt.plusDays(7);
        this.book = book;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void calculatePrice() {
        LocalDate returnedAt = LocalDate.now();
        long daysPassed = ChronoUnit.DAYS.between(returnDate, returnedAt);
        double pricePerDay = 1.0;
        price = price + (daysPassed * pricePerDay);
    }

    public LocalDate getRentedAt() {
        return rentedAt;
    }

    public void setRentedAt() {
        rentedAt = LocalDate.now();
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate() {
        returnDate = returnDate.plusDays(7);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Rent rent = (Rent) o;
        return Objects.equals(id, rent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", price=" + price +
                ", rentedAt=" + rentedAt +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", customer=" + customer +
                '}';
    }
}
