package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.AuthorInputDTO;
import br.com.douglasbello.bookstore.dtos.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.RequestResponseDTO;
import br.com.douglasbello.bookstore.dtos.mapper.Mapper;
import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository repository;

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
        return ResponseEntity.ok().body(new AuthorResponseDTO(repository.save(author)));
    }
}
