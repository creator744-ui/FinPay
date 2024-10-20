package com.finpay.user_service.dtos;
import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
}