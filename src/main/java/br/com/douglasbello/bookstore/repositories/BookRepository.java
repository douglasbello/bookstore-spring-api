package br.com.douglasbello.bookstore.repositories;

import br.com.douglasbello.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
