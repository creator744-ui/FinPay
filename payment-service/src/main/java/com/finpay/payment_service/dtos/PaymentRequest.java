package com.finpay.payment_service.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentRequest {
    private BigDecimal amount;
    private String currency;
    private String paymentMethod; // e.g., CREDIT_CARD, BANK_TRANSFER
    private UUID invoiceId; // Reference to Invoice managed by Invoice Service
    private String paymentGateway; // e.g., Stripe, PayPal
}
