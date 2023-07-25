package br.com.douglasbello.bookstore.repositories;

import br.com.douglasbello.bookstore.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    User findUserByUsername(String username);
    UserDetails findByUsername(String username);
}