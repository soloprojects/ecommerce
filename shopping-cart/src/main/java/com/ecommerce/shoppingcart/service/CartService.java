package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.client.BookClient;
import com.ecommerce.shoppingcart.config.JwtService;
import com.ecommerce.shoppingcart.dto.BookResponse;
import com.ecommerce.shoppingcart.dto.CartRequest;
import com.ecommerce.shoppingcart.dto.CartResponse;
import com.ecommerce.shoppingcart.entity.Cart;
import com.ecommerce.shoppingcart.entity.User;
import com.ecommerce.shoppingcart.exception.BusinessException;
import com.ecommerce.shoppingcart.iservice.ICartService;
import com.ecommerce.shoppingcart.repository.CartRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final BookClient bookClient;
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    @Override
    @Cacheable(value = "addToCart", key = "#request.getHeader('Authorization')")
    public List<CartResponse> userCartContent(HttpServletRequest request) {

        String userEmail = jwtService.extractUsernameFromRequest(request);
        User user = userService.findByEmail(userEmail);
        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());
        if(cartList.size() == 0){
            throw new BusinessException("Cart is empty",HttpStatus.NOT_FOUND);
        }
        List<CartResponse> responseList = new ArrayList<>();
        for(Cart cart : cartList){
            CartResponse response = new CartResponse();
            response.setId(cart.getId());
            response.setQuantity(cart.getQuantity());
            response.setAmount(cart.getAmount());
            response.setBookResponse(bookClient.findByBookId(cart.getBookId()));
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    @Cacheable(value = "addToCart", key = "#request.getHeader('Authorization')")
    public void addToCart(CartRequest requestDto, HttpServletRequest request) {
        String userEmail = jwtService.extractUsernameFromRequest(request);
        User user = userService.findByEmail(userEmail);
        Optional<Cart> cartCheck = cartRepository.findByBookId(requestDto.getBookId());
        if(cartCheck.isPresent()){
            throw new BusinessException("Cart Item already exists", HttpStatus.IM_USED);
        }
        Cart cart = new Cart();
        cart.setBookId(requestDto.getBookId());
        cart.setUser(user);
        cart.setQuantity(requestDto.getQuantity());
        cart.setAmount(requestDto.getAmount());
        cartRepository.save(cart);

    }

    @Override
    public void delete(Long Id) {
        Cart cart = cartRepository.findById(Id).orElseThrow();
        cartRepository.delete(cart);
    }



}
