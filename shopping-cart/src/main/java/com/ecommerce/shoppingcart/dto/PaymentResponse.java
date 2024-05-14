package com.ecommerce.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private Long id;

    private String reference_no;

    private String PaymentStatus;

    private String PaymentMode;

}
