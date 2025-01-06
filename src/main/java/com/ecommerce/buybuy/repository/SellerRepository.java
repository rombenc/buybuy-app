package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, String> {
}
