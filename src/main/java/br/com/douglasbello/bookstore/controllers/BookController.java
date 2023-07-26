package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.BookDTO;
import br.com.douglasbello.bookstore.dtos.BookInsertionDTO;
import br.com.douglasbello.bookstore.dtos.RequestResponseDTO;
import br.com.douglasbello.bookstore.dtos.mapper.Mapper;
import br.com.douglasbello.bookstore.services.AuthorService;
import br.com.douglasbello.bookstore.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostMapping(value = "/books")
    public ResponseEntity<?> save(@Valid @RequestBody BookInsertionDTO dto) {
        if (authorService.findById(dto.getAuthorId()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Author not found."));
        }
        return ResponseEntity.ok().body(new BookDTO(bookService.save(Mapper.bookInsertionDtoToBook(dto))));
    }
}