package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.request.CustomerRegisterRequest;
import com.ecommerce.buybuy.dto.response.RegisterResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.Customer;
import com.ecommerce.buybuy.entity.ShoppingCart;
import com.ecommerce.buybuy.entity.User;
import com.ecommerce.buybuy.repository.CustomerRepository;
import com.ecommerce.buybuy.service.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    private <T> WebResponse<T> buildErrorResponse(Exception e) {
        return new WebResponse<>(500, "Error: " + e.getMessage(), null);
    }

    //todo: create get all product
    //todo: checkout and payment
    //todo: get shop
    //todo: delete account or deactivate account
}

