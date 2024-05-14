package com.ecommerce.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/BookServiceFallBack")
    public String orderServiceFallback() {
        return "Book Service is down!";
    }

    @GetMapping("/PaymentServiceFallBack")
    public String paymentServiceFallback() {
        return "Payment Service is down!";
    }

    @GetMapping("/CartServiceFallBack")
    public String productServiceFallback() {
        return "Cart Service is down!";
    }

}
