package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.author.AuthorInputDTO;
import br.com.douglasbello.bookstore.dtos.author.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.RequestResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.Mapper;
import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<Set<AuthorResponseDTO>> findAll() {
        return ResponseEntity.ok().body(authorService.findAll().stream().map(AuthorResponseDTO::new).collect(Collectors.toSet()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        if (authorService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Author not found."));
        }
        return ResponseEntity.ok().body(new AuthorResponseDTO(authorService.findById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<AuthorResponseDTO>> findAuthorsByName(@PathVariable String name) {
        name = name.replace("-", " ");
        return ResponseEntity.ok().body(authorService.findAuthorByName(name).stream().map(AuthorResponseDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody AuthorInputDTO dto) {
        Author author = new Author();
        if (!author.setBirthDate(dto.getBirthDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResponseDTO(HttpStatus.BAD_REQUEST.value(), "Invalid birth date."));
        }
        if (dto.getDeathDate() != null && !author.setDeathDate(dto.getDeathDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResponseDTO(HttpStatus.BAD_REQUEST.value(), "Invalid death date."));
        }
        author = Mapper.toAuthor(dto);
        return ResponseEntity.ok().body(new AuthorResponseDTO(authorService.save(author)));
    }
}
