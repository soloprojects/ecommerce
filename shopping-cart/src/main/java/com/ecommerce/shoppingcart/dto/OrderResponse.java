package com.ecommerce.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    public Long id;

    public Double totalAmount;

    public PaymentResponse paymentResponse;

}
