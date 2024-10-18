package com.finpay.authentication.dtos;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private UUID id;
    private String name;
    private String email;
}