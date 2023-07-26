package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.author.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.book.BookDTO;
import br.com.douglasbello.bookstore.dtos.book.BookInsertionDTO;
import br.com.douglasbello.bookstore.dtos.util.RequestResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.Mapper;
import br.com.douglasbello.bookstore.services.AuthorService;
import br.com.douglasbello.bookstore.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> dtos = bookService.findAll().stream()
                .map(book -> {
                    BookDTO dto = new BookDTO(book);
                    dto.setAuthor(new AuthorResponseDTO(authorService.findById(book.getAuthor().getId())));
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping(value = "/books")
    public ResponseEntity<?> save(@Valid @RequestBody BookInsertionDTO dto) {
        if (authorService.findById(dto.getAuthorId()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Author not found."));
        }
        return ResponseEntity.ok().body(new BookDTO(bookService.save(Mapper.bookInsertionDtoToBook(dto))));
    }
}