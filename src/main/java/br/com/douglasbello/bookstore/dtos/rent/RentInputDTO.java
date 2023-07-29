package br.com.douglasbello.bookstore.dtos.rent;

import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class RentInputDTO {
    @JsonIgnore
    private Book book;
    @JsonIgnore
    private Customer customer;

    public RentInputDTO() {
    }

    public RentInputDTO(Book book, Customer customer) {
        this.book = book;
        this.customer = customer;
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
}