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

    public WebResponse<RegisterResponse> registerCustomer(CustomerRegisterRequest request) {
        try {
            Customer newCustomer = buildCustomerFromRequest(request);
            newCustomer.setVerificationCode(emailService.generateVerificationCode());
            newCustomer.setVerified(false);

            Customer savedCustomer = customerRepository.save(newCustomer);
            emailService.sendVerificationEmail(savedCustomer);

            RegisterResponse response = RegisterResponse.builder()
                    .id(savedCustomer.getId())
                    .email(savedCustomer.getEmail())
                    .role("CUSTOMER")
                    .message("Registration successful. Please verify your email.")
                    .verificationCode(savedCustomer.getVerificationCode())
                    .build();

            return new WebResponse<>(200, "Success", response);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    private Customer buildCustomerFromRequest(CustomerRegisterRequest request) {
        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setShippingAddress(request.getShippingAddress());

        ShoppingCart cart = new ShoppingCart();
        cart.setCustomer(customer);
        customer.setShoppingCart(cart);

        return customer;
    }

    private <T> WebResponse<T> buildErrorResponse(Exception e) {
        return new WebResponse<>(500, "Error: " + e.getMessage(), null);
    }
}

