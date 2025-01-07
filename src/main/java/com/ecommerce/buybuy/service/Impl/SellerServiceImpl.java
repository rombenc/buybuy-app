package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.request.CustomerRegisterRequest;
import com.ecommerce.buybuy.dto.request.SellerRegisterRequest;
import com.ecommerce.buybuy.dto.response.RegisterResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.Customer;
import com.ecommerce.buybuy.entity.Seller;
import com.ecommerce.buybuy.entity.ShoppingCart;
import com.ecommerce.buybuy.repository.CustomerRepository;
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

    public WebResponse<RegisterResponse> registerSeller(SellerRegisterRequest request) {
        try {
            Seller newSeller = buildSellerFromRequest(request);
            newSeller.setVerificationCode(emailService.generateVerificationCode());
            newSeller.setVerified(false);

            Seller savedSeller = sellerRepository.save(newSeller);
            emailService.sendVerificationEmail(savedSeller);

            RegisterResponse response = RegisterResponse.builder()
                    .id(savedSeller.getId())
                    .email(savedSeller.getEmail())
                    .role("SELLER")
                    .message("Registration successful. Please verify your email.")
                    .verificationCode(savedSeller.getVerificationCode())
                    .build();

            return new WebResponse<>(200, "Success", response);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    private Seller buildSellerFromRequest(SellerRegisterRequest request) {
        Seller seller = new Seller();
        seller.setEmail(request.getEmail());
        seller.setPassword(passwordEncoder.encode(request.getPassword()));
        seller.setFirstName(request.getFirstName());
        seller.setLastName(request.getLastName());
        seller.setStoreName(request.getStoreName());
        seller.setStoreDescription(request.getStoreDescription());
        seller.setBusinessRegistrationNumber(request.getBusinessRegistrationNumber());
        seller.setBusinessAddress(request.getBusinessAddress());
        return seller;
    }
    private <T> WebResponse<T> buildErrorResponse(Exception e) {
        return new WebResponse<>(500, "Error: " + e.getMessage(), null);
    }
}
