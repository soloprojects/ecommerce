package com.ecommerce.shoppingcart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {


    @Schema(example= "5000.50", description = "Total Amount")
    @NotNull(message = "Total Amount shouldn't be null")
    public double totalAmount;
    @NotNull(message = "User Id shouldn't be null")
    @Schema(example= "1", description = "User Id")
    public Long userId;

}
