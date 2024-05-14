package com.ecommerce.shoppingcart.repository;

import com.ecommerce.shoppingcart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUserId(Long Id);
    Optional<Cart> findByBookId(String bookId);

}
