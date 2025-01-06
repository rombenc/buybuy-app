package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
}
