package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    @PostMapping(value = "/books")
    public Book save(@RequestBody Book book) {
        return repository.save(book);
    }
}