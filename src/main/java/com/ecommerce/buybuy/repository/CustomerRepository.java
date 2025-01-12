package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Object> findByEmail(String username);
}
