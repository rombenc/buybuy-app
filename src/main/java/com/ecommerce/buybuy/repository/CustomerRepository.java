package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
