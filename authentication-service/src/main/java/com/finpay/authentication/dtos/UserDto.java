package com.finpay.authentication.dtos;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String email;
    private String password;
    private String name;
    private Set<String> roles;
}
