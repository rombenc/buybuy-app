package com.ecommerce.buybuy.entity;

import com.ecommerce.buybuy.constant.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class Role {
    @Id
    private Integer Id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserType role;
}
