package com.finpay.user_service.models;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Role {
    ROLE_USER(Set.of(Permission.READ_USER)),
    ROLE_ADMIN(Set.of(Permission.READ_USER, Permission.WRITE_USER, Permission.DELETE_USER, Permission.READ_INVOICE, Permission.WRITE_INVOICE, Permission.DELETE_INVOICE)),
    ROLE_MANAGER(Set.of(Permission.READ_USER, Permission.WRITE_USER, Permission.READ_INVOICE, Permission.WRITE_INVOICE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

}
