package br.com.douglasbello.bookstore.repositories;

import br.com.douglasbello.bookstore.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Integer> {
}