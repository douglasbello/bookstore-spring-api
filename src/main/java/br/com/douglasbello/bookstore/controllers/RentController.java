package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.author.AuthorResponseDTO;
import br.com.douglasbello.bookstore.dtos.rent.RentInputDTO;
import br.com.douglasbello.bookstore.dtos.rent.RentResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.Mapper;
import br.com.douglasbello.bookstore.dtos.util.RequestResponseDTO;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.Rent;
import br.com.douglasbello.bookstore.services.AuthorService;
import br.com.douglasbello.bookstore.services.BookService;
import br.com.douglasbello.bookstore.services.CustomerService;
import br.com.douglasbello.bookstore.services.RentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books/rent")
public class RentController {
    private final RentService rentService;
    private final BookService bookService;
    private final CustomerService customerService;
    private final AuthorService authorService;

    public RentController(RentService rentService, BookService bookService, CustomerService customerService, AuthorService authorService) {
        this.rentService = rentService;
        this.bookService = bookService;
        this.customerService = customerService;
        this.authorService = authorService;
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<?> findById(@PathVariable Integer id) {
//        return ResponseEntity.ok().body(new RentResponseDTO(rentService.findById(id)));
//    }

    @PostMapping(value = "/{bookId}")
    public ResponseEntity<?> save(@NotNull(message = "Book id cannot be null.") @Positive(message = "Book id must be positive") @PathVariable Integer bookId) {
        if (bookService.findById(bookId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Book not found."));
        }
        RentResponseDTO response = rentService.initializeRentResponseDto(bookId, customerService.getCurrentCustomer());
        return ResponseEntity.ok().body(response);
    }
}