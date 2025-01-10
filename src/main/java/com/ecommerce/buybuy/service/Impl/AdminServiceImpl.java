package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.response.UserResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.User;
import com.ecommerce.buybuy.mapper.UserMapper;
import com.ecommerce.buybuy.repository.AdminRepository;
import com.ecommerce.buybuy.repository.UserRepository;
import com.ecommerce.buybuy.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;


    private <T> WebResponse<T> buildErrorResponse(Exception e) {
        return new WebResponse<>(500, "Error: " + e.getMessage(), null);
    }

    //todo: create get user, seller, product
    private List<UserResponse> getAllUser() {
        return
                userRepository.findAll().stream()
                        .map(userMapper::toUserResponse)
                        .collect(Collectors.toList());
    }

    //todo: delete and ban user or seller
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    //todo: get transaction
}
