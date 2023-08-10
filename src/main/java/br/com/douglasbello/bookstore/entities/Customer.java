package br.com.douglasbello.bookstore.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.douglasbello.bookstore.entities.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "customers" )
public class Customer implements UserDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cpf;
    private UserRole role;
    @ManyToMany
    @JoinTable(
            name = "customer_bought_books",
            joinColumns = @JoinColumn( name = "customer_id" ),
            inverseJoinColumns = @JoinColumn( name = "book_id" )
    )
    private List<Book> boughtBooks = new ArrayList<>();
    @OneToOne( mappedBy = "customer" )
    private Rent rentedBook;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String username, String password, String cpf, UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.role = role;
    }

    public Customer(String firstName, String lastName, String username, String password, String cpf) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if ( this.role == UserRole.ADMIN ) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
