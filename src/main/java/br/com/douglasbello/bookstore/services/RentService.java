package br.com.douglasbello.bookstore.services;

import br.com.douglasbello.bookstore.dtos.author.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.rent.RentInputDTO;
import br.com.douglasbello.bookstore.dtos.rent.RentResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.Mapper;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.entities.Rent;
import br.com.douglasbello.bookstore.entities.enums.BookStatus;
import br.com.douglasbello.bookstore.repositories.RentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final BookService bookService;
    private final AuthorService authorService;

    public RentService(RentRepository rentRepository, BookService bookService, AuthorService authorService) {
        this.rentRepository = rentRepository;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public Rent findById(Integer id) {
        return rentRepository.findById(id).orElse(null);
    }

    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    public RentResponseDTO initializeRentResponseDto(Integer bookId, Customer customer) {
        Book book = bookService.findById(bookId);
        book = bookService.updateStatus(book, BookStatus.RENTED);
        RentInputDTO dto = new RentInputDTO(book,customer);
        Rent rent = Mapper.toRent(dto);
        rent = save(rent);
        RentResponseDTO response = new RentResponseDTO(rent);
        response.getBook().setAuthor(new AuthorResponseDTO(authorService.findById(book.getAuthor().getId())));
        return response;
    }
}