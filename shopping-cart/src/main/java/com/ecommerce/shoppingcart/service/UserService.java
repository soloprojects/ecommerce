package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.config.JwtService;
import com.ecommerce.shoppingcart.dto.AuthenticationResponse;
import com.ecommerce.shoppingcart.entity.User;
import com.ecommerce.shoppingcart.exception.BusinessException;
import com.ecommerce.shoppingcart.iservice.IUserService;
import com.ecommerce.shoppingcart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private JwtService jwtService;

    private AuthenticationService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException("Email not found", HttpStatus.NOT_FOUND));
    }

}
