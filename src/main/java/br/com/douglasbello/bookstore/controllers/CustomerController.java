package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.CustomerDTO;
import br.com.douglasbello.bookstore.dtos.SignInDTO;
import br.com.douglasbello.bookstore.dtos.mapper.Mapper;
import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@Valid @RequestBody SignInDTO dto) {
        Customer customer = Mapper.signInDtoToCustomer(dto);
        return ResponseEntity.ok().body(new CustomerDTO(customerService.save(customer)));
    }
}