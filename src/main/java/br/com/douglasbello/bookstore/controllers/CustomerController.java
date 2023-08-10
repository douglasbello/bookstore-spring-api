package br.com.douglasbello.bookstore.controllers;

import br.com.douglasbello.bookstore.dtos.customer.CustomerResponseDTO;
import br.com.douglasbello.bookstore.dtos.customer.LoginDTO;
import br.com.douglasbello.bookstore.dtos.customer.SignInDTO;
import br.com.douglasbello.bookstore.dtos.util.Mapper;
import br.com.douglasbello.bookstore.dtos.util.RequestResponseDTO;
import br.com.douglasbello.bookstore.dtos.util.TokenDTO;
import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.security.TokenService;
import br.com.douglasbello.bookstore.services.impl.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public CustomerController(CustomerService customerService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping( value = "/sign-in" )
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInDTO dto) {
        if ( customerService.findCustomerByUsername(dto.getUsername()) != null ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestResponseDTO(HttpStatus.CONFLICT.value(), "Username already in use."));
        }
        if ( customerService.findCustomerByCpf(dto.getCpf()) != null ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestResponseDTO(HttpStatus.CONFLICT.value(), "CPF already in use."));
        }
        Customer customer = Mapper.signInDtoToCustomer(dto);
        return ResponseEntity.ok().body(new CustomerResponseDTO(customerService.save(customer)));
    }

    @PostMapping( value = "/login" )
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Customer) auth.getPrincipal());

            return ResponseEntity.ok().body(new TokenDTO(token));
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResponseDTO(HttpStatus.BAD_REQUEST.value(), "Invalid username or password."));
        }
    }

    @PutMapping( value = "/update" )
    public ResponseEntity<?> update(@Valid @RequestBody SignInDTO dto) {
        if ( customerService.findCustomerByUsername(dto.getUsername()) != null ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestResponseDTO(HttpStatus.CONFLICT.value(), "Username already in use."));
        }
        if ( customerService.findCustomerByCpf(dto.getCpf()) != null ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestResponseDTO(HttpStatus.CONFLICT.value(), "CPF already in use."));
        }

        Customer _new = Mapper.signInDtoToCustomer(dto);
        CustomerResponseDTO response = new CustomerResponseDTO(customerService.update(customerService.getCurrentCustomer().getId(), _new));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping( value = "/delete ")
    public ResponseEntity<?> delete() {
        Integer customerId = customerService.getCurrentCustomer().getId();
        customerService.delete(customerId);
        return ResponseEntity.noContent().build();
    }
}
