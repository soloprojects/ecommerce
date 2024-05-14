package com.ecommerce.shoppingcart.client;

import com.ecommerce.shoppingcart.dto.PaymentResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface PaymentClient {

    @GetExchange("/api/v1/payment-service/order/{id}")
    public PaymentResponse findByOrderId(@PathVariable("id") Long orderId);

}
