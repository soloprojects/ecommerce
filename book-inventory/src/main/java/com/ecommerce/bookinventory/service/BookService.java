package com.ecommerce.bookinventory.service;

import com.ecommerce.bookinventory.dto.BookDto;
import com.ecommerce.bookinventory.exception.BusinessException;
import com.ecommerce.bookinventory.model.BookModel;
import com.ecommerce.bookinventory.model.Genre;
import com.ecommerce.bookinventory.repository.BookRepository;
import com.ecommerce.bookinventory.service.service_interface.BookServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;

    @Override
    @Cacheable(value = "findById", key = "#id")
    public BookModel findById(String id) {
        return bookRepository.findById(id).
                orElseThrow(() -> new BusinessException("Data not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Iterable<BookModel> findAll(){
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value = "search", key="#searchQuery")
    public List<BookModel> search(String searchQuery) throws IOException {
        //Create query on multiple fields
        return bookRepository.multiMatchQuery(searchQuery);

    }

    @Override
    public void save(BookDto bookDto) {
        BookModel book = new BookModel();
        book.setTitle(bookDto.title);
        book.setYearOfPublication(bookDto.yearOfPublication);
        book.setAuthor(bookDto.author);
        book.setGenre(Genre.validateString(bookDto.genre));
        book.setIsbn_code(bookDto.isbn_code);
        bookRepository.save(book);
    }
}
