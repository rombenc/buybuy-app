package com.ecommerce.buybuy.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RefreshTokenResponse {
    private String token;
    private String expirationTime;
    private String message;
    private int statusCode;
}
