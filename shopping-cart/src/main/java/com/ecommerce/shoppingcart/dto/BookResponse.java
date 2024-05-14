package com.ecommerce.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String id;

    private String title;

    private Integer yearOfPublication;

    private String genre;

    private String author;

    private String isbn_code;

}
