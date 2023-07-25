package br.com.douglasbello.bookstore.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cpf;
    @ManyToMany
    @JoinTable(
            name = "customer_bought_books",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> boughtBooks = new ArrayList<>();
    @OneToOne(mappedBy = "customer")
    private Rent rentedBook;

    public Customer(){}

    public Customer(String firstName, String lastName, String username, String password, String cpf) {
        this.username = username;
        this.password = password;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Book> getBoughtBooks() {
        return boughtBooks;
    }

    public Rent getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(Rent rentedBook) {
        this.rentedBook = rentedBook;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cpf='" + cpf + '\'' +
                ", boughtBooks=" + boughtBooks +
                ", rentedBooks=" + rentedBook +
                '}';
    }
}