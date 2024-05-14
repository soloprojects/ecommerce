package com.ecommerce.shoppingcart.controller;

import com.ecommerce.shoppingcart.dto.CartRequest;
import com.ecommerce.shoppingcart.exception.BusinessException;
import com.ecommerce.shoppingcart.service.CartService;
import com.ecommerce.shoppingcart.service.OrderService;
import com.ecommerce.shoppingcart.utility.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/shopping-cart/")
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("/items")
    public ResponseEntity<Object> getItem(HttpServletRequest request){
        try{
            return ResponseHandler.generateResponse("Successfully obtained item", HttpStatus.OK, cartService.userCartContent(request));
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error " + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@Valid @RequestBody CartRequest cartRequest, HttpServletRequest request){
        try{
            cartService.addToCart(cartRequest, request);
            return ResponseHandler.generateResponse("Successfully created", HttpStatus.CREATED, null);
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PostMapping("/checkout")
    public ResponseEntity<Object> checkout(HttpServletRequest request){
        try{
            orderService.create(request);
            return ResponseHandler.generateResponse("Successfully created purchase order", HttpStatus.CREATED, null);
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping("/purchase_history/user/{id}")
    public ResponseEntity<Object> getItem(@PathVariable("id") Long id){
        try{
            return ResponseHandler.generateResponse("Successfully obtained item", HttpStatus.OK, orderService.userPurchaseList(id));
        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error" + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

}
