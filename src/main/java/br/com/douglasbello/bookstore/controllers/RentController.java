package br.com.douglasbello.bookstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglasbello.bookstore.dtos.rent.PriceDTO;
import br.com.douglasbello.bookstore.dtos.rent.RentResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.RequestResponseDTO;
import br.com.douglasbello.bookstore.entities.Rent;
import br.com.douglasbello.bookstore.services.impl.BookService;
import br.com.douglasbello.bookstore.services.impl.CustomerService;
import br.com.douglasbello.bookstore.services.impl.RentService;

@RestController
@RequestMapping( "/api/books/rent" )
public class RentController {
    private final RentService rentService;
    private final BookService bookService;
    private final CustomerService customerService;

    public RentController(RentService rentService, BookService bookService, CustomerService customerService) {
        this.rentService = rentService;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @GetMapping( value = "/me" )
    public ResponseEntity<?> findCurrentCustomerRent() {
        if ( rentService.findRentByCustomer(customerService.getCurrentCustomer()) == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Customer doesn't have any book rented."));
        }
        return ResponseEntity.ok().body(new RentResponseDTO(rentService.findRentByCustomer(customerService.getCurrentCustomer())));
    }

    @PostMapping( value = "/{bookId}" )
    public ResponseEntity<?> rentABook(@PathVariable Integer bookId) {
        if ( bookService.findById(bookId) == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Book not found."));
        }
        RentResponseDTO response = rentService.initializeRentResponseDto(bookId, customerService.getCurrentCustomer());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/return")
    public ResponseEntity<?> returnBook(@PathVariable Integer bookId) {
        if (bookService.findById(bookId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestResponseDTO(HttpStatus.NOT_FOUND.value(), "Book not found."));
        }

        Rent rent = rentService.findRentByBook(bookService.findById(bookId));
        rent.calculatePrice();

        double price = rent.getPrice();
        rentService.delete(rent.getId());
        return ResponseEntity.ok().body(new PriceDTO(price));
    }
}
