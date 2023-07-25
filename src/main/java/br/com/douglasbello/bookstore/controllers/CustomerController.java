package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/customers")
    Customer save(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
}