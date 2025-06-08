package com.finpay.payment_service.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class RefundRequest {
    private UUID paymentId;
    private BigDecimal amount;
    private String reason;
}
