package br.com.douglasbello.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "authors" )
public class Author {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Integer age;
    @JsonIgnore
    @OneToMany( mappedBy = "author" )
    private List<Book> publishedBooks = new ArrayList<>();

    public Author() {
    }

    public Author(Integer id, String firstName, String lastName, LocalDate birthDate, LocalDate deathDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        fullName = firstName + " " + lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        setAge();
    }

    public Author(String firstName, String lastName, String birthDate, String deathDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        fullName = firstName + " " + lastName;
        setBirthDate(birthDate);
        setDeathDate(deathDate);
        setAge();
    }

    public Author(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        fullName = firstName + " " + lastName;
        setBirthDate(birthDate);
        setAge();
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
        fullName = firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        fullName = firstName + " " + lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean setBirthDate(String birthDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.birthDate = LocalDate.parse(birthDate, formatter);
            setAge();
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public boolean setDeathDate(String deathDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.deathDate = LocalDate.parse(deathDate, formatter);
            setAge();
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public Integer getAge() {
        return age;
    }

    public void setAge() {
        if ( deathDate != null ) {
            Period period = Period.between(birthDate, deathDate);
            this.age = period.getYears();
            return;
        }

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        this.age = period.getYears();
    }

    public List<Book> getBooks() {
        return publishedBooks;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Author author = (Author) o;
        return id == author.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", publishedBooks=" + publishedBooks +
                '}';
    }
}