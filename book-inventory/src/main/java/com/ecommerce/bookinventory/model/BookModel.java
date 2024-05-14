package com.ecommerce.bookinventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "book_index")
public class BookModel implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Integer, name = "year")
    private Integer yearOfPublication;

    @Field(type = FieldType.Text, name = "genre")
    private String genre;

    @Field(type = FieldType.Text, name = "author")
    private String author;

    @Field(type = FieldType.Keyword, name = "isbn_code")
    private String isbn_code;
}
