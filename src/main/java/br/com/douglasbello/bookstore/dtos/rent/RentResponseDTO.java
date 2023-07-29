package br.com.douglasbello.bookstore.dtos.rent;


import br.com.douglasbello.bookstore.dtos.book.BookResponseDTO;
import br.com.douglasbello.bookstore.dtos.customer.CustomerResponseDTO;
import br.com.douglasbello.bookstore.entities.Rent;

import java.time.format.DateTimeFormatter;

public class RentResponseDTO {
    private Integer id;
    private Double price;
    private String rentedAt;
    private String returnDate;
    private BookResponseDTO book;
    private CustomerResponseDTO customer;

    public RentResponseDTO() {
    }

    public RentResponseDTO(Rent rent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.id = rent.getId();
        this.price = rent.getPrice();
        this.rentedAt = rent.getRentedAt().format(formatter);
        this.returnDate = rent.getReturnDate().format(formatter);
        this.book = new BookResponseDTO(rent.getBook());
        this.customer = new CustomerResponseDTO(rent.getCustomer());
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

    public String getRentedAt() {
        return rentedAt;
    }

    public void setRentedAt(String rentedAt) {
        this.rentedAt = rentedAt;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BookResponseDTO getBook() {
        return book;
    }

    public void setBook(BookResponseDTO book) {
        this.book = book;
    }

    public CustomerResponseDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseDTO customer) {
        this.customer = customer;
    }
}
