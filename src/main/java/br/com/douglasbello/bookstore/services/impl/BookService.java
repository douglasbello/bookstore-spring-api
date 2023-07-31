package br.com.douglasbello.bookstore.services.impl;

import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.enums.BookStatus;
import br.com.douglasbello.bookstore.repositories.BookRepository;
import br.com.douglasbello.bookstore.services.Common;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements Common<Book> {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Integer id, Book _new) {
        try {
            Book old = bookRepository.getReferenceById(id);
            updateData(old, _new);
            return bookRepository.save(old);
        } catch (EntityNotFoundException exception) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Integer bookId) {
        try {
            bookRepository.deleteById(bookId);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new RuntimeException();
        }
    }

    public List<Book> findAllByStatus(BookStatus status) {
        return bookRepository.findAllByStatus(status);
    }

    public List<Book> findAllByAuthor(Author author) {
        return bookRepository.findAllByAuthor(author);
    }

    public List<Book> findAllBooksByTitle(String title) {
        title = title.replace("-", " ");
        return bookRepository.findAllByTitleContainingIgnoreCase(title);
    }

    private void updateData(Book old, Book _new) {
        old.setTitle(_new.getTitle());
        old.setOverview(_new.getOverview());
        old.setPublishedYear(_new.getPublishedYear());
        old.setSalePrice(_new.getSalePrice());
        old.setStatus(_new.getStatus());
    }

    public Book updateStatus(Book book, BookStatus status) {
        book.setStatus(status);
        return update(book.getId(), book);
    }
}