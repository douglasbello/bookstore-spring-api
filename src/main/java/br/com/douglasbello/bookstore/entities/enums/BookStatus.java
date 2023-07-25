package br.com.douglasbello.bookstore.entities.enums;

public enum BookStatus {
    AVAILABLE("AVAILABLE"),
    RENTED("RENTED"),
    SOLD("SOLD");

    public String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}