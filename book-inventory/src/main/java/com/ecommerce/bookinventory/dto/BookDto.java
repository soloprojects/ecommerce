package com.ecommerce.bookinventory.dto;

import com.ecommerce.bookinventory.metadata.ISBNConstraint;
import jakarta.validation.constraints.*;
import lombok.*;

//@Getter
//@Setter
public class BookDto {

    public BookDto(){

    }
    @NotEmpty(message = "The above field must not be blank.")
    public String title;
    @Positive
    @NotNull(message = "The above field must not be blank.")
    public Integer yearOfPublication;
    @NotEmpty(message = "The above field must not be blank.")
    public String author;

    @NotEmpty(message = "The above field must not be blank.")
    public String genre;
    @NotEmpty(message = "The above field must not be blank.")
    @ISBNConstraint
    @Pattern(regexp = "^[0-9-]+$", message = "ISBN code must contain only numbers and dashes")
    public String isbn_code;

    public String getTitle() {
        return title;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn_code() {
        return isbn_code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsbn_code(String isbn_code) {
        this.isbn_code = isbn_code;
    }
}
