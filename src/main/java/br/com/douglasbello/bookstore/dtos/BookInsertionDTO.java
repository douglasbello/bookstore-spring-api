package br.com.douglasbello.bookstore.dtos;

import jakarta.validation.constraints.*;

public class BookInsertionDTO {
    @NotBlank(message = "Title cannot be null or blank.")
    @Size(max = 100, message = "Title size must be between 1 and 50 characters.")
    private String title;
    @NotBlank(message = "Overview cannot be null or blank.")
    @Size(max = 1000, message = "Overview size must be between 20 and 1000 characters.")
    private String overview;
    @NotNull(message = "Published year cannot be null.")
    @Positive(message = "Published year must be a positive.")
    @Max(value = 2023, message = "Published year must be a valid year.")
    private Integer publishedYear;
    @NotNull(message = "Sale price cannot be null.")
    @DecimalMin(value = "1.0", message = "Price cannot be less than 1.0")
    private Double salePrice;
    @NotNull(message = "Author id cannot be null.")
    @Positive(message = "Author id cannot be negative.")
    private Integer authorId;

    public BookInsertionDTO(){}

    public BookInsertionDTO(String title, String overview, Integer publishedYear, Double salePrice, Integer authorId) {
        this.title = title;
        this.overview = overview;
        this.publishedYear = publishedYear;
        this.salePrice = salePrice;
        this.authorId = authorId;
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

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
