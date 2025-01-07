package com.ecommerce.buybuy.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private Long id;
    private String email;
    private String role;
    private String message;
    private String verificationCode;
}