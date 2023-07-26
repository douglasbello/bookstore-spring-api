package br.com.douglasbello.bookstore.dtos.book;

import br.com.douglasbello.bookstore.dtos.author.AuthorResponseDTO;
import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.enums.BookStatus;

import java.util.Objects;

public class BookDTO {
    private Integer id;
    private String title;
    private String overview;
    private Double salePrice;
    private BookStatus status;
    private AuthorResponseDTO author;

    public BookDTO(){}

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.overview = book.getOverview();
        this.salePrice = book.getSalePrice();
        this.status = book.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public AuthorResponseDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorResponseDTO author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", salePrice=" + salePrice +
                ", status=" + status +
                ", author=" + author +
                '}';
    }
}