package br.com.douglasbello.bookstore.dtos.customer;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank( message = "Username cannot be blank." ) String username,
                       @NotBlank( message = "Username cannot be blank." ) String password) {
}
