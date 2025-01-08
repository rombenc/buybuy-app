package com.ecommerce.buybuy.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String token;
    private String role;
    private String expirationTime;
    private String message;
    private int statusCode;
}
