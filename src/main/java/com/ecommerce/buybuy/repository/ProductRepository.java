package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
