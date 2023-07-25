package br.com.douglasbello.bookstore.repositories;

import br.com.douglasbello.bookstore.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}