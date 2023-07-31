package br.com.douglasbello.bookstore.services.impl;

import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.repositories.AuthorRepository;
import br.com.douglasbello.bookstore.services.Common;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AuthorService implements Common<Author> {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findById(Integer id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Integer old, Author _new) {
        try {
            Author entity = authorRepository.findById(old).get();
            updateData(entity, _new);
            return authorRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            authorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException emptyResultDataAccessException) {
            throw new RuntimeException();
        }
    }

    public List<Author> findAuthorByName(String name) {
        return authorRepository.findAuthorsByFullNameContainingIgnoreCase(name);
    }

    private void updateData(Author old, Author _new) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        old.setFirstName(_new.getFirstName());
        old.setLastName(_new.getLastName());
        old.setBirthDate(_new.getBirthDate().format(formatter));
        old.setDeathDate(_new.getDeathDate().format(formatter));
    }
}