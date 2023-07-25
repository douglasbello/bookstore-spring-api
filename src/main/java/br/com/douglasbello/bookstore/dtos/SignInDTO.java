package br.com.douglasbello.bookstore.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignInDTO {
    @NotNull(message = "First name cannot be null.")
    @Size(max = 100, message = "First name cannot be bigger than 100.")
    private String firstName;
    @NotNull(message = "First name cannot be null.")
    @Size(max = 100, message = "First last name cannot be bigger than 100.")
    private String lastName;
    @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters.")
    private String username;
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters.")
    private String password;
    @Size(min = 11, max = 11, message = "CPF must have exactly 11 characters.")
    private String cpf;

    public SignInDTO(){}

    public SignInDTO(@NotNull(message = "First name cannot be null.") String firstName, @NotNull(message = "First name cannot be null.") String lastName, String username, String password, String cpf) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cpf = cpf;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}