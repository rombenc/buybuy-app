package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.response.ProductResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.Product;
import com.ecommerce.buybuy.repository.CustomerRepository;
import com.ecommerce.buybuy.repository.ProductRepository;
import com.ecommerce.buybuy.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ProductRepository productRepository;

    private <T> WebResponse<T> buildErrorResponse(Exception e) {
        return new WebResponse<>(500, "Error: " + e.getMessage(), null);
    }

    //todo: checkout and payment
    //todo: update profile
    //todo: get shop
    //todo: delete account or deactivate account
}

