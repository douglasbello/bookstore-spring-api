package entities;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private String username;
    private String password;

    private String document;

    private List<Book> boughtBooks = new ArrayList<>();

    private List<Book> rentedBooks = new ArrayList<>();

    public Customer(){}

    public Customer(long id, String firstName, String lastName, String username, String password, String document) {
        super(id,firstName,lastName);
        this.username = username;
        this.password = password;
        this.document = document;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<Book> getBoughtBooks() {
        return boughtBooks;
    }

    public List<Book> getRentedBooks() {
        return rentedBooks;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", document='" + document + '\'' +
                ", boughtBooks=" + boughtBooks +
                ", rentedBooks=" + rentedBooks +
                "} " + super.toString();
    }
}