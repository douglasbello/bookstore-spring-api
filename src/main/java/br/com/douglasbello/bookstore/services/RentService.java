package br.com.douglasbello.bookstore.services;

import br.com.douglasbello.bookstore.entities.Rent;
import br.com.douglasbello.bookstore.repositories.RentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RentService {
    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
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
}