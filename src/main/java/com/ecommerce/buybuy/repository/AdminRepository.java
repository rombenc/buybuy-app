package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.Admin;
import com.ecommerce.buybuy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
