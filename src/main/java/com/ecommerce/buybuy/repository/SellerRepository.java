package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.Seller;
import com.ecommerce.buybuy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, String> {
    Optional<User> findByEmail(String email);

    String id(Long id);
}
