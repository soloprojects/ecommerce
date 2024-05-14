package com.ecommerce.shoppingcart.iservice;

import com.ecommerce.shoppingcart.dto.CartRequest;
import com.ecommerce.shoppingcart.dto.CartResponse;
import com.ecommerce.shoppingcart.entity.Cart;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ICartService {

    List<CartResponse> userCartContent(HttpServletRequest request);

    void addToCart(CartRequest requestDto, HttpServletRequest request);

    void delete(Long Id);


}
