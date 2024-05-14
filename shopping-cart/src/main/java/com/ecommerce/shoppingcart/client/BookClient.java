package com.ecommerce.shoppingcart.client;

import com.ecommerce.shoppingcart.dto.BookResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface BookClient {

    @GetExchange("/api/v1/book-inventory/item/{id}")
    public BookResponse findByBookId(@PathVariable("id") String bookId);

}
