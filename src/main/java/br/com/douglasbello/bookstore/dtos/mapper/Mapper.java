package br.com.douglasbello.bookstore.dtos.mapper;

import br.com.douglasbello.bookstore.dtos.AuthorInputDTO;
import br.com.douglasbello.bookstore.entities.Author;

public class Mapper {

    public static Author toAuthor(AuthorInputDTO dto) {
        Author author = new Author(dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getDeathDate());
        return author;
    }
}