package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.request.AdminRegisterRequest;
import com.ecommerce.buybuy.dto.request.CustomerRegisterRequest;
import com.ecommerce.buybuy.dto.response.RegisterResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.Admin;
import com.ecommerce.buybuy.entity.Customer;
import com.ecommerce.buybuy.entity.ShoppingCart;
import com.ecommerce.buybuy.repository.AdminRepository;
import com.ecommerce.buybuy.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    private <T> WebResponse<T> buildErrorResponse(Exception e) {
        return new WebResponse<>(500, "Error: " + e.getMessage(), null);
    }

    //todo: create get user, seller, product
    //todo: delete and ban user or seller
    //todo: get transaction
}
