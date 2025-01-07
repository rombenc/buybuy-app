package com.ecommerce.buybuy.controller;

import com.ecommerce.buybuy.dto.request.AdminRegisterRequest;
import com.ecommerce.buybuy.dto.request.CustomerRegisterRequest;
import com.ecommerce.buybuy.dto.request.SellerRegisterRequest;
import com.ecommerce.buybuy.dto.response.RegisterResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.service.Impl.AdminServiceImpl;
import com.ecommerce.buybuy.service.Impl.CustomerServiceImpl;
import com.ecommerce.buybuy.service.Impl.SellerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CustomerServiceImpl customerRegistrationService;
    private final SellerServiceImpl sellerRegistrationService;
    private final AdminServiceImpl adminRegistrationService;

    @PostMapping("/registercustomer")
    public WebResponse<RegisterResponse> registerCustomer(@Valid @RequestBody CustomerRegisterRequest request) {
        return customerRegistrationService.registerCustomer(request);
    }

    @PostMapping("/registerseller")
    public WebResponse<RegisterResponse> registerSeller(@Valid @RequestBody SellerRegisterRequest request) {
        return sellerRegistrationService.registerSeller(request);
    }

    @PostMapping("/registeradmin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public WebResponse<RegisterResponse> registerAdmin(@Valid @RequestBody AdminRegisterRequest request) {
        return adminRegistrationService.registerAdmin(request);
    }

    @GetMapping("/verify")
    public WebResponse<String> verifyEmail(@RequestParam String code) {
        try {
            // Implement verification logic
            return new WebResponse<>(200, "Success", "Email verified successfully");
        } catch (Exception e) {
            return new WebResponse<>(400, e.getMessage(), null);
        }
    }
}