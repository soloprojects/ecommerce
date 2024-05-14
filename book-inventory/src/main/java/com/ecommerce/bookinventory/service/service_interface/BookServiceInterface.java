package com.ecommerce.bookinventory.service.service_interface;

import com.ecommerce.bookinventory.dto.BookDto;
import com.ecommerce.bookinventory.model.BookModel;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface BookServiceInterface {

    BookModel findById(String id);

    List<BookModel> search(String query) throws IOException;

    Iterable<BookModel> findAll();
    void save(BookDto book);

}
