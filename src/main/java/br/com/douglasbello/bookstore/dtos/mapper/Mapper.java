package br.com.douglasbello.bookstore.dtos.mapper;

import br.com.douglasbello.bookstore.dtos.AuthorInputDTO;
import br.com.douglasbello.bookstore.dtos.SignInDTO;
import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.entities.enums.UserRole;

public class Mapper {

    public static Author toAuthor(AuthorInputDTO dto) {
        Author author = new Author(dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getDeathDate());
        return author;
    }

    public static Customer signInDtoToCustomer(SignInDTO dto) {
        Customer customer = new Customer(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getPassword(), dto.getCpf(), UserRole.USER);
        return customer;
    }
}