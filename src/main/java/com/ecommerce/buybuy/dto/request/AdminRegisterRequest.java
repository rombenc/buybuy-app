package com.ecommerce.buybuy.dto.request;

import com.ecommerce.buybuy.constant.AdminLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminRegisterRequest extends UserRegisterRequest {
    @NotNull(message = "Employee ID is required")
    private Long employeeId;
    @NotNull(message = "Department is required")
    private String department;
    @NotNull(message = "Admin level is required")
    private AdminLevel adminLevel;
}