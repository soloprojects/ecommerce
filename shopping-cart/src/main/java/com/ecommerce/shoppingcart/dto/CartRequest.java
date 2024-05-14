package com.ecommerce.shoppingcart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    @Schema(example= "Wisdom is Profitable to Direct", description = "Book Id")
    @NotNull(message = "book shouldn't be null")
    public String bookId;
    @Schema(example= "5", description = "Quantity of books")
    @NotNull(message = "Quantity shouldn't be null")
    public Integer quantity;

    @Schema(example= "5000", description = "Price of book")
    @NotNull(message = "Quantity shouldn't be null")
    public Double amount;

}
