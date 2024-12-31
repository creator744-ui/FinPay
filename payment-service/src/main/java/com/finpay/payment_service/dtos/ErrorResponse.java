package com.finpay.payment_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private UUID errorId;
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
