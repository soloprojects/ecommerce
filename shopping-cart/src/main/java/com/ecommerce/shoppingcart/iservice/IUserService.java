package com.ecommerce.shoppingcart.iservice;


import com.ecommerce.shoppingcart.dto.AuthenticationResponse;
import com.ecommerce.shoppingcart.entity.User;

public interface IUserService {
     User findByEmail(String email);

}
