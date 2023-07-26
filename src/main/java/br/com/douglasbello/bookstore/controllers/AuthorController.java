package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.AuthorInputDTO;
import br.com.douglasbello.bookstore.dtos.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.RequestResponseDTO;
import br.com.douglasbello.bookstore.dtos.mapper.Mapper;
import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.repositories.AuthorRepository;
import br.com.douglasbello.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/authors")
    public Set<AuthorResponseDTO> authors() {
        return authorService.findAll().stream().map(author -> new AuthorResponseDTO(author)).collect(Collectors.toSet());
    }

    @PostMapping(value = "/authors")
    public ResponseEntity<?> save(@RequestBody AuthorInputDTO dto) {
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
