package com.ecommerce.shoppingcart.service;

import com.ecommerce.shoppingcart.client.PaymentClient;
import com.ecommerce.shoppingcart.config.JwtService;
import com.ecommerce.shoppingcart.dto.*;
import com.ecommerce.shoppingcart.entity.Cart;
import com.ecommerce.shoppingcart.entity.Order;
import com.ecommerce.shoppingcart.entity.User;
import com.ecommerce.shoppingcart.exception.BusinessException;
import com.ecommerce.shoppingcart.iservice.IOrderService;
import com.ecommerce.shoppingcart.repository.CartRepository;
import com.ecommerce.shoppingcart.repository.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;
    private final JwtService jwtService;
    private final UserService userService;
    private final CartRepository cartRepository;
    private final CartService cartService;

    @Override
    public List<OrderResponse> userPurchaseList(Long Id) {

        List<Order> orderList = orderRepository.findAllByUserId(Id);
        if(orderList.size() == 0){
            throw new BusinessException("Purchase history is empty", HttpStatus.NOT_FOUND);
        }
        List<OrderResponse> responseList = new ArrayList<>();
        for(Order order : orderList){
            OrderResponse response = new OrderResponse();
            response.setId(order.getId());
            response.setTotalAmount(order.getTotalAmount());
            response.setPaymentResponse(paymentClient.findByOrderId(order.getId()));
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    @Transactional
    public void create(HttpServletRequest request) {

        String userEmail = jwtService.extractUsernameFromRequest(request);
        User user = userService.findByEmail(userEmail);
        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());
        Double totalAmount = 0.00;
        if(cartList.size() == 0){
            throw new BusinessException("Cart is empty",HttpStatus.NOT_FOUND);
        }
        for(Cart cart : cartList){
            totalAmount += cart.getAmount();
            cartService.delete(cart.getId());
        }
        Order order = new Order();
        order.setTotalAmount(totalAmount);
        order.setUser(user);
        orderRepository.save(order);

    }

}
