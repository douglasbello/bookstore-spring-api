package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.author.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.book.BookResponseDTO;
import br.com.douglasbello.bookstore.dtos.book.BookInsertionDTO;
import br.com.douglasbello.bookstore.dtos.util.RequestResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.Mapper;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.enums.BookStatus;
import br.com.douglasbello.bookstore.services.AuthorService;
import br.com.douglasbello.bookstore.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        List<BookResponseDTO> dtos = bookService.findAll().stream()
                .map(book -> {
                    BookResponseDTO dto = new BookResponseDTO(book);
                    dto.setAuthor(new AuthorResponseDTO(authorService.findById(book.getAuthor().getId())));
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody BookInsertionDTO dto) {
        if (authorService.findById(dto.getAuthorId()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Author not found."));
        }
        return ResponseEntity.ok().body(new BookResponseDTO(bookService.save(Mapper.bookInsertionDtoToBook(dto))));
    }

    @PostMapping(value = "/{bookId}/sell")
    public ResponseEntity<?> sellBook(@PathVariable Integer bookId) {
        if (bookService.findById(bookId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Book not found."));
        }
        BookResponseDTO response = new BookResponseDTO(bookService.findById(bookId));
        response.setStatus(BookStatus.SOLD);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<?> findAllBooksByStatus(@PathVariable String status) {
        try {
            BookStatus bookStatus = BookStatus.valueOf(status.toUpperCase());
            List<BookResponseDTO> response = bookService.findAllByStatus(BookStatus.valueOf(status.toUpperCase())).stream().map(book -> {
                        BookResponseDTO _new = new BookResponseDTO(book);
                        _new.setAuthor(new AuthorResponseDTO(authorService.findById(book.getAuthor().getId())));
                        return _new;
                    }).collect(Collectors.toList());

            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Invalid status, the status are: AVAILABLE, RENTED, SOLD"));
        }
    }
}