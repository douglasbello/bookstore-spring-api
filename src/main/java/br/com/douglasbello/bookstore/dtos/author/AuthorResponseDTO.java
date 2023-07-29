package br.com.douglasbello.bookstore.dtos.author;

import br.com.douglasbello.bookstore.entities.Author;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AuthorResponseDTO {
    private Integer id;
    private String name;
    private String birthDate;
    private String deathDate;
    private Integer age;

    public AuthorResponseDTO() {
    }

    public AuthorResponseDTO(Author author) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.id = author.getId();
        this.name = author.getFullName();
        this.birthDate = author.getBirthDate().format(formatter);
        if ( author.getDeathDate() != null ) {
            this.deathDate = author.getDeathDate().format(formatter);
        }
        this.age = author.getAge();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        AuthorResponseDTO that = (AuthorResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthorResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", deathDate='" + deathDate + '\'' +
                ", age=" + age +
                '}';
    }
}