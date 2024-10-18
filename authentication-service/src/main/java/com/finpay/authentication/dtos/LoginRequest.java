package com.finpay.authentication.dtos;
import lombok.*;
@Data
public class LoginRequest {
    private String email;
    private String password;
}
