package br.com.douglasbello.bookstore.mock;

import br.com.douglasbello.bookstore.entities.Author;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.Customer;
import br.com.douglasbello.bookstore.entities.Rent;
import br.com.douglasbello.bookstore.entities.enums.BookStatus;
import br.com.douglasbello.bookstore.services.AuthorService;
import br.com.douglasbello.bookstore.services.BookService;
import br.com.douglasbello.bookstore.services.CustomerService;
import br.com.douglasbello.bookstore.services.RentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("test")
public class Mocking implements CommandLineRunner {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final RentService rentService;

    public Mocking(AuthorService authorService, BookService bookService, CustomerService customerService, RentService rentService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.customerService = customerService;
        this.rentService = rentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Author khaled = new Author("Khaled", "Hosseini", "04/03/1965");
        authorService.save(khaled);

        Book kiteRunner = new Book("The Kite Runner", "The Kite Runner is the first novel by Afghan-American author Khaled Hosseini. Published in 2003 by Riverhead Books, it tells the story of Amir, a young boy from the Wazir Akbar Khan district of Kabul."
        ,2003,35.99);
        kiteRunner = bookService.save(kiteRunner);
        kiteRunner.setAuthor(khaled);
        kiteRunner = bookService.save(kiteRunner);

        Book thousandSplendidSuns = new Book("A Thousand Splendid Suns", "Propelled by the same superb instinct for storytelling that made The Kite Runner a beloved classic, the #1 New York Times bestseller A Thousand Splendid Suns is at once an incredible chronicle of thirty years of Afghan history and a deeply moving story of family, friendship, faith, and the salvation to be found in love. "
        , 2008,14.99);
        thousandSplendidSuns = bookService.save(thousandSplendidSuns);
        thousandSplendidSuns.setAuthor(khaled);
        bookService.save(thousandSplendidSuns);

        Customer napster = new Customer("Douglas", "Bello", "douglasbello", "douglasbello","99999999999");
        napster = customerService.save(napster);

//        Rent rent = new Rent(07.80,kiteRunner,napster);
//        kiteRunner.setStatus(BookStatus.RENTED);
//        bookService.save(kiteRunner);
//        rentService.save(rent);
    }
}
