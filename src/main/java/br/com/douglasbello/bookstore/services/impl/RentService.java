package br.com.douglasbello.bookstore.services.impl;

import br.com.douglasbello.bookstore.dtos.author.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.rent.RentInputDTO;
import br.com.douglasbello.bookstore.dtos.rent.RentResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.Mapper;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.entities.Rent;
import br.com.douglasbello.bookstore.entities.enums.BookStatus;
import br.com.douglasbello.bookstore.repositories.BookRepository;
import br.com.douglasbello.bookstore.repositories.RentRepository;
import br.com.douglasbello.bookstore.services.Common;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService implements Common<Rent> {
    private final RentRepository rentRepository;
    private final BookService bookService;
    private final AuthorService authorService;

    public RentService(RentRepository rentRepository, BookService bookService, AuthorService authorService) {
        this.rentRepository = rentRepository;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public Rent findById(Integer id) {
        return rentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public Rent update(Integer old, Rent _new) {
        return null;
    }

    @Override
    public void delete(Integer rentId) {
        try {
            rentRepository.deleteById(rentId);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new RuntimeException();
        }
    }

    public Rent findRentByCustomer(Customer customer) {
        return rentRepository.findRentByCustomer(customer);
    }

    public Rent findRentByBook(Book book) {
        return rentRepository.findRentByBook(book);
    }

    public RentResponseDTO initializeRentResponseDto(Integer bookId, Customer customer) {
        Book book = bookService.findById(bookId);
        book = bookService.updateStatus(book, BookStatus.RENTED);
        RentInputDTO dto = new RentInputDTO(book, customer);
        Rent rent = Mapper.toRent(dto);
        rent = save(rent);
        RentResponseDTO response = new RentResponseDTO(rent);
        response.getBook().setAuthor(new AuthorResponseDTO(authorService.findById(book.getAuthor().getId())));
        return response;
    }
}
