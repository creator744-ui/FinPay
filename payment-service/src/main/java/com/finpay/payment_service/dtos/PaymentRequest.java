package com.finpay.payment_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class PaymentRequest {
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private UUID invoiceId;
    private String paymentGateway;
}
