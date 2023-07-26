package br.com.douglasbello.bookstore.services;

import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.enums.BookStatus;
import br.com.douglasbello.bookstore.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Integer id, Book _new) {
        try {
            Book old = bookRepository.getReferenceById(id);
            updateData(old, _new);
            return bookRepository.save(old);
        } catch (EntityNotFoundException exception) {
            throw new RuntimeException();
        }
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