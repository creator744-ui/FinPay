package com.finpay.authentication.dtos;
import lombok.*;
@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
}