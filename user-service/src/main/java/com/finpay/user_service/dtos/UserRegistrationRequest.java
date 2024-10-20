package com.finpay.user_service.dtos;
import lombok.*;

@Data
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;
}