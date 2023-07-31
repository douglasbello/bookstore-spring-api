package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.rent.RentResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.RequestResponseDTO;
import br.com.douglasbello.bookstore.services.impl.AuthorService;
import br.com.douglasbello.bookstore.services.impl.BookService;
import br.com.douglasbello.bookstore.services.impl.CustomerService;
import br.com.douglasbello.bookstore.services.impl.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/books/rent" )
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

    @GetMapping( value = "/me" )
    public ResponseEntity<?> findCurrentCustomerRent() {
        if ( rentService.findRentByCustomer(customerService.getCurrentCustomer()) == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Customer doesn't have any book rented."));
        }
        return ResponseEntity.ok().body(new RentResponseDTO(rentService.findRentByCustomer(customerService.getCurrentCustomer())));
    }

    @PostMapping( value = "/{bookId}" )
    public ResponseEntity<?> save(@PathVariable Integer bookId) {
        if ( bookService.findById(bookId) == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Book not found."));
        }
        RentResponseDTO response = rentService.initializeRentResponseDto(bookId, customerService.getCurrentCustomer());
        return ResponseEntity.ok().body(response);
    }
}