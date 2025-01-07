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
