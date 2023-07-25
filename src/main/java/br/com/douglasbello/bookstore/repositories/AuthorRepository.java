package br.com.douglasbello.bookstore.repositories;

import br.com.douglasbello.bookstore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
