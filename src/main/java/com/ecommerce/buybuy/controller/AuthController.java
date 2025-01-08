package com.ecommerce.buybuy.controller;

import com.ecommerce.buybuy.dto.request.AdminRegisterRequest;
import com.ecommerce.buybuy.dto.request.CustomerRegisterRequest;
import com.ecommerce.buybuy.dto.request.SellerRegisterRequest;
import com.ecommerce.buybuy.dto.response.RegisterResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.service.Impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/registercustomer")
    public WebResponse<RegisterResponse> registerCustomer(@Valid @RequestBody CustomerRegisterRequest request) {
        return authService.registerCustomer(request);
    }

    @PostMapping("/registerseller")
    public WebResponse<RegisterResponse> registerSeller(@Valid @RequestBody SellerRegisterRequest request) {
        return authService.registerSeller(request);
    }

    @PostMapping("/registeradmin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public WebResponse<RegisterResponse> registerAdmin(@Valid @RequestBody AdminRegisterRequest request) {
        return authService.registerAdmin(request);
    }

    @PostMapping("/verify")
    public ResponseEntity<WebResponse<String>> verifyAccount(@RequestParam String email, @RequestParam String code) {
        return ResponseEntity.ok(authService.verifyAccount(email, code));
    }
}