package br.com.douglasbello.bookstore.dtos.author;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class AuthorInputDTO {
    private Integer id;
    @NotBlank( message = "Last name cannot be blank." )
    private String firstName;
    @NotBlank( message = "Last name cannot be blank." )
    private String lastName;
    @NotBlank( message = "Birth date cannot be blank." )
    private String birthDate;
    private String deathDate;

    public AuthorInputDTO() {
    }

    public AuthorInputDTO(Integer id, String firstName, String lastName, String birthDate, String deathDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        AuthorInputDTO authorInputDto = (AuthorInputDTO) o;
        return Objects.equals(id, authorInputDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthorInputDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", deathDate='" + deathDate + '\'' +
                '}';
    }
}