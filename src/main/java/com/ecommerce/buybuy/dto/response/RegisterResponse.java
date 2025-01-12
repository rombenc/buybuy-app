package com.ecommerce.buybuy.dto.response;

import com.ecommerce.buybuy.constant.UserType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private Long id;
    private String fullName;
    private String email;
    private UserType role;
    private String message;
    private String verificationCode;
}