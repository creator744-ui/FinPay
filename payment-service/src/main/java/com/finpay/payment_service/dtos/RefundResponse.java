package com.finpay.payment_service.dtos;

import com.finpay.payment_service.models.RefundStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RefundResponse {
    private UUID id;
    private String refundReference;
    private RefundStatus status;
    private BigDecimal amount;
    private String reason;
    private LocalDateTime createdAt;
}
