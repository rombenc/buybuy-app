package com.ecommerce.buybuy.dto.request;

import com.ecommerce.buybuy.entity.Address;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerRegisterRequest extends UserRegisterRequest {
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String phoneNumber;
}