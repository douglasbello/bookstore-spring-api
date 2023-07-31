package br.com.douglasbello.bookstore.services.impl;

import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.repositories.CustomerRepository;
import br.com.douglasbello.bookstore.services.Common;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements UserDetailsService, Common<Customer> {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Integer old, Customer _new) {
        try {
            Customer oldCustomer = findById(old);
            updateData(oldCustomer, _new);
            return customerRepository.save(oldCustomer);
        } catch (EntityNotFoundException exception) {
            throw new RuntimeException();
        }
    }

    @Override
    public Customer save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Integer customerId) {
        try {
            customerRepository.deleteById(customerId);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException e) {
            throw new RuntimeException();
        }
    }

    public Customer findCustomerByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    public Customer findCustomerByCpf(String cpf) {
        return customerRepository.findCustomerByCpf(cpf);
    }

    public Customer getCurrentCustomer() {
        Customer currentCustomer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = findCustomerByUsername(currentCustomer.getUsername());
        return customer;
    }

    private void updateData(Customer old, Customer _new) {
        old.setFirstName(_new.getFirstName());
        old.setLastName(_new.getLastName());
        old.setUsername(_new.getUsername());
        old.setPassword(_new.getPassword());
        old.setCpf(_new.getCpf());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByUsername(username);
    }
}