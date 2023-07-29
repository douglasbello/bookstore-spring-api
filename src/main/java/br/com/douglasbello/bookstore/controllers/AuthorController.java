package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.author.AuthorInputDTO;
import br.com.douglasbello.bookstore.dtos.author.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.book.BookWithoutActorDTO;
import br.com.douglasbello.bookstore.dtos.util.RequestResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.Mapper;
import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.services.AuthorService;
import br.com.douglasbello.bookstore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/authors" )
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Set<AuthorResponseDTO>> findAll() {
        return ResponseEntity.ok().body(authorService.findAll().stream().map(AuthorResponseDTO::new).collect(Collectors.toSet()));
    }

    @GetMapping( "/id/{id}" )
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        if ( authorService.findById(id) == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Author not found."));
        }
        return ResponseEntity.ok().body(new AuthorResponseDTO(authorService.findById(id)));
    }

    @GetMapping( "/name/{name}" )
    public ResponseEntity<List<AuthorResponseDTO>> findAuthorsByName(@PathVariable String name) {
        name = name.replace("-", " ");
        return ResponseEntity.ok().body(authorService.findAuthorByName(name).stream().map(AuthorResponseDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody AuthorInputDTO dto) {
        Author author = new Author();
        if ( !author.setBirthDate(dto.getBirthDate()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResponseDTO(HttpStatus.BAD_REQUEST.value(), "Invalid birth date. The date format is: dd/MM/yyyy"));
        }
        if ( dto.getDeathDate() != null && !author.setDeathDate(dto.getDeathDate()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResponseDTO(HttpStatus.BAD_REQUEST.value(), "Invalid death date. The date format is: dd/MM/yyyy"));
        }
        author = Mapper.toAuthor(dto);
        return ResponseEntity.ok().body(new AuthorResponseDTO(authorService.save(author)));
    }

    @PutMapping( "/update/{authorId}" )
    public ResponseEntity<?> updateAuthor(@PathVariable Integer authorId, @RequestBody AuthorInputDTO _new) {
        if ( authorService.findById(authorId) == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Author not found."));
        }
        AuthorResponseDTO response = new AuthorResponseDTO(authorService.update(authorId, Mapper.toAuthor(_new)));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping( "/delete/{authorId}" )
    public ResponseEntity<?> deleteAuthor(@PathVariable Integer authorId) {
        if ( authorService.findById(authorId) == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Author not found."));
        }
        authorService.deleteById(authorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping( "/{authorId}/books" )
    public ResponseEntity<?> findAllBooksOfAuthor(@PathVariable Integer authorId) {
        if ( authorService.findById(authorId) == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Author not found."));
        }
        Author author = authorService.findById(authorId);
        List<BookWithoutActorDTO> response = bookService.findAllByAuthor(author).stream().map(BookWithoutActorDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }
}