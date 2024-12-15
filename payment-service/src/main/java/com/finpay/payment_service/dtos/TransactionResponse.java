package com.finpay.payment_service.dtos;

import com.finpay.payment_service.models.TransactionStatus;
import com.finpay.payment_service.models.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private UUID id;
    private String transactionReference;
    private TransactionType type;
    private TransactionStatus status;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
