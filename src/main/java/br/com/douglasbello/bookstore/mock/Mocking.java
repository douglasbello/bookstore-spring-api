package br.com.douglasbello.bookstore.mock;

import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.services.AuthorService;
import br.com.douglasbello.bookstore.services.BookService;
import br.com.douglasbello.bookstore.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class Mocking implements CommandLineRunner {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CustomerService customerService;

    public Mocking(AuthorService authorService, BookService bookService, CustomerService customerService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.customerService = customerService;
    }


    @Override
    public void run(String... args) throws Exception {
        Author khaled = new Author("Khaled", "Hosseini", "04/03/1965");
        authorService.save(khaled);

        Book kiteRunner = new Book("The Kite Runner", "The Kite Runner is the first novel by Afghan-American author Khaled Hosseini. Published in 2003 by Riverhead Books, it tells the story of Amir, a young boy from the Wazir Akbar Khan district of Kabul."
        ,2003,35.99);
        kiteRunner = bookService.save(kiteRunner);
        kiteRunner.setAuthor(khaled);
        bookService.save(kiteRunner);

        Customer napster = new Customer("Douglas", "Bello", "napster", "douglasbello","85523577049");
        customerService.save(napster);
    }
}
