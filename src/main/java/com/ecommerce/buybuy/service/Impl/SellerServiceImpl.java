package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.repository.SellerRepository;
import com.ecommerce.buybuy.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl {
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    private <T> WebResponse<T> buildErrorResponse(Exception e) {
        return new WebResponse<>(500, "Error: " + e.getMessage(), null);
    }

    //todo: create product
    //todo: get all product from shops
    //todo: get bill
    //todo: get shipping details or invoice
}
