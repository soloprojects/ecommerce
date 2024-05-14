package com.ecommerce.shoppingcart.controller;

import com.ecommerce.shoppingcart.config.JwtService;
import com.ecommerce.shoppingcart.dto.AuthenticationResponse;
import com.ecommerce.shoppingcart.dto.PasswordRequest;
import com.ecommerce.shoppingcart.dto.RegisterRequest;
import com.ecommerce.shoppingcart.entity.User;
import com.ecommerce.shoppingcart.exception.BusinessException;
import com.ecommerce.shoppingcart.service.AuthenticationService;
import com.ecommerce.shoppingcart.service.UserService;
import com.ecommerce.shoppingcart.utility.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/shopping-cart")
public class RegistrationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RegisterRequest userModel, final HttpServletRequest request) {
        try{
            AuthenticationResponse authResponse = authenticationService.register(userModel);
            return ResponseHandler.generateResponse("User created successfully", HttpStatus.CREATED, authResponse);

        }catch(BusinessException ex){
            return ResponseHandler.generateResponse("Error " + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }catch(Exception ex){
            return ResponseHandler.generateResponse("An unexpected error occurred" + ex.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
        }

}
