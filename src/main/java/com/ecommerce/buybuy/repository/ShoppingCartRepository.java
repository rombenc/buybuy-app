package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
}
