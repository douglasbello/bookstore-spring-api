package br.com.douglasbello.bookstore.dtos.util;

import br.com.douglasbello.bookstore.dtos.author.AuthorInputDTO;
import br.com.douglasbello.bookstore.dtos.book.BookInsertionDTO;
import br.com.douglasbello.bookstore.dtos.customer.SignInDTO;
import br.com.douglasbello.bookstore.dtos.rent.RentInputDTO;
import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.entities.Rent;
import br.com.douglasbello.bookstore.entities.enums.UserRole;
import br.com.douglasbello.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    private static AuthorService authorService;

    public static Author toAuthor(AuthorInputDTO dto) {
        Author author = new Author(dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getDeathDate());
        return author;
    }

    public static Customer signInDtoToCustomer(SignInDTO dto) {
        Customer customer = new Customer(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getPassword(), dto.getCpf(), UserRole.USER);
        return customer;
    }

    public static Book bookInsertionDtoToBook(BookInsertionDTO dto) {
        Book book = new Book(dto.getTitle(), dto.getOverview(), dto.getPublishedYear(), dto.getSalePrice());
        book.setAuthor(authorService.findById(dto.getAuthorId()));
        return book;
    }

    public static Rent toRent(RentInputDTO dto) {
        Double rentPrice = dto.getBook().getSalePrice() * 0.3;
        Rent rent = new Rent(rentPrice, dto.getBook(), dto.getCustomer());
        return rent;
    }
}