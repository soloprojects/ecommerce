package com.ecommerce.shoppingcart.iservice;

import com.ecommerce.shoppingcart.dto.RoleRequest;
import com.ecommerce.shoppingcart.entity.Role;

import java.util.List;

public interface IRole {
    Role findById(Long roleId);
    List<Role> getAll();
    Role saveRole(RoleRequest role);
}
