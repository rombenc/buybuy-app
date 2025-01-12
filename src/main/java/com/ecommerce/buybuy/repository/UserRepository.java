package com.ecommerce.buybuy.repository;

import com.ecommerce.buybuy.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    String email(@Email(message = "Enter The Correct email") String email);
}
