package com.ecommerce.paymentservice.controller;

import com.ecommerce.paymentservice.Dto.PaymentRequest;
import com.ecommerce.paymentservice.entity.Payment;
import com.ecommerce.paymentservice.exception.BusinessException;
import com.ecommerce.paymentservice.service.PaymentService;
import com.ecommerce.paymentservice.utility.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment-service")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/order/{id}")
    public ResponseEntity<Object> getItem(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(paymentService.getPaymentByOrderId(id));
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PostMapping("/accept")
    public ResponseEntity<Object> create(@Valid @RequestBody PaymentRequest request){
        try{
            paymentService.doPayment(request);
            return ResponseHandler.generateResponse("Successfully created", HttpStatus.CREATED, null);
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

}
