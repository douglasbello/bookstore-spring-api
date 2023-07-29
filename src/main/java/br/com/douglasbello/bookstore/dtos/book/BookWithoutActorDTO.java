package br.com.douglasbello.bookstore.dtos.book;

import br.com.douglasbello.bookstore.entities.Book;
import br.com.douglasbello.bookstore.entities.enums.BookStatus;

public class BookWithoutActorDTO {
    private Integer id;
    private String title;
    private String overview;
    private Double salePrice;
    private BookStatus status;

    public BookWithoutActorDTO(){}

    public BookWithoutActorDTO(Book book) {
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
}
