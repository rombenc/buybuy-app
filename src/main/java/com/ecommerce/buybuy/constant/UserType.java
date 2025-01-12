package com.ecommerce.buybuy.constant;

public enum UserType {
    ADMIN, CUSTOMER, SELLER;

    public String getRole() {
        return this.name();
    }
}
