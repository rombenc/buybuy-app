package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.request.*;
import com.ecommerce.buybuy.dto.response.LoginResponse;
import com.ecommerce.buybuy.dto.response.RefreshTokenResponse;
import com.ecommerce.buybuy.dto.response.RegisterResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.*;
import com.ecommerce.buybuy.repository.AdminRepository;
import com.ecommerce.buybuy.repository.CustomerRepository;
import com.ecommerce.buybuy.repository.SellerRepository;
import com.ecommerce.buybuy.repository.UserRepository;
import com.ecommerce.buybuy.security.JwtService;
import com.ecommerce.buybuy.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public WebResponse<RegisterResponse> registerAdmin(AdminRegisterRequest request) {
        try {
            Admin newAdmin = buildAdminFromRequest(request);
            newAdmin.setVerificationCode(emailService.generateVerificationCode());
            newAdmin.setVerified(false);

            Admin savedAdmin = adminRepository.save(newAdmin);
            emailService.sendVerificationEmail(savedAdmin);

            RegisterResponse response = RegisterResponse.builder()
                    .id(savedAdmin.getId())
                    .email(savedAdmin.getEmail())
                    .role("ADMIN")
                    .message("Registration successful. Please verify your email.")
                    .verificationCode(savedAdmin.getVerificationCode())
                    .build();

            return new WebResponse<>(200, "Success", response);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

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

    public WebResponse<String> verifyAccount(String email, String verificationCode) {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            if (user.getVerificationCode().equals(verificationCode)) {
                user.setVerified(true);
                user.setVerificationCode(null); // Clear kode verifikasi setelah berhasil
                userRepository.save(user);
                return new WebResponse<>(200, "Email verified successfully", "Verified");
            } else {
                return new WebResponse<>(400, "Invalid verification code", null);
            }
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    public WebResponse<LoginResponse> login(LoginRequest loginRequest) {
        try {
            authenticateUser(loginRequest);
            User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            String jwt = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            LoginResponse response = LoginResponse.builder()
                    .token(jwt)
                    .role(user.getUserType())
                    .expirationTime("24Hrs")
                    .message("Successfully logged in")
                    .build();

            return new WebResponse<>(200, "Success", response);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    public WebResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        try {
            String email = jwtService.extractUsername(refreshTokenRequest.getToken());
            User user = userRepository.findByEmail(email).orElseThrow();

            if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
                String newToken = jwtService.generateToken(user);

                RefreshTokenResponse response = RefreshTokenResponse.builder()
                        .token(newToken)
                        .expirationTime("24Hr")
                        .message("Successfully refreshed token")
                        .build();

                return new WebResponse<>(200, "Success", response);
            }
            return new WebResponse<>(400, "Invalid token", null);
        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    private void authenticateUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
    }

    private Seller buildSellerFromRequest(SellerRegisterRequest request) {
        return getSeller(request, passwordEncoder);
    }

    static Seller getSeller(SellerRegisterRequest request, PasswordEncoder passwordEncoder) {
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

    private Customer buildCustomerFromRequest(CustomerRegisterRequest request) {
        return getCustomer(request, passwordEncoder);
    }

    static Customer getCustomer(CustomerRegisterRequest request, PasswordEncoder passwordEncoder) {
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

    private Admin buildAdminFromRequest(AdminRegisterRequest request) {
        Admin admin = new Admin();
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setEmployeeId(request.getEmployeeId());
        admin.setDepartment(request.getDepartment());
        admin.setAdminLevel(request.getAdminLevel());
        return admin;
    }

    private <T> WebResponse<T> buildErrorResponse(Exception e) {
        return new WebResponse<>(500, "Error: " + e.getMessage(), null);
    }
}
