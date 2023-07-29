package br.com.douglasbello.bookstore.services;

import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public List<Author> findAuthorByName(String name) {
        return authorRepository.findAuthorsByFullNameContainingIgnoreCase(name);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Integer old, Author _new) {
        try {
            Author entity = authorRepository.findById(old).get();
            updateData(entity, _new);
            return authorRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void deleteById(Integer id) {
        try {
            authorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException emptyResultDataAccessException) {
            throw new RuntimeException();
        }
    }

    private void updateData(Author old, Author _new) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        old.setFirstName(_new.getFirstName());
        old.setLastName(_new.getLastName());
        old.setBirthDate(_new.getBirthDate().format(formatter));
        old.setDeathDate(_new.getDeathDate().format(formatter));
    }
}