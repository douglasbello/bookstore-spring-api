package br.com.douglasbello.bookstore.repositories;

import br.com.douglasbello.bookstore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAuthorsByFullNameContainingIgnoreCase(String fullName);
}
