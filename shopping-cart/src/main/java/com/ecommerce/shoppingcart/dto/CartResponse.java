package com.ecommerce.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    public Long id;

    public Integer quantity;

    public Double amount;

    public BookResponse bookResponse;

}
