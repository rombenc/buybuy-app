package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<Cart, String> {
}
