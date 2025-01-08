package com.ecommerce.buybuy.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
