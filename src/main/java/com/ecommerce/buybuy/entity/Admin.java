package com.ecommerce.buybuy.entity;

import com.ecommerce.buybuy.constant.AdminLevel;
import com.ecommerce.buybuy.constant.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("ADMIN")
@Getter
@Setter
public class Admin extends User{

    @Enumerated
    private AdminLevel adminLevel;

    private String department;

    private Long employeeId;

    @Override
    public UserType getUserType() {
        return UserType.ADMIN;
    }
}
