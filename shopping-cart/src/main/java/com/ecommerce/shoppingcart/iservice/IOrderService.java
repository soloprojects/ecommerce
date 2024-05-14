package com.ecommerce.shoppingcart.iservice;

import com.ecommerce.shoppingcart.dto.OrderRequest;
import com.ecommerce.shoppingcart.dto.OrderResponse;
import com.ecommerce.shoppingcart.entity.Order;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IOrderService {

    List<OrderResponse> userPurchaseList(Long Id);

    void create(HttpServletRequest request);

}
