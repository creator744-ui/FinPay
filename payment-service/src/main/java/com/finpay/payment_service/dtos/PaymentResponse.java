package com.finpay.payment_service.dtos;

import com.finpay.payment_service.models.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PaymentResponse {
    private UUID id;
    private String paymentReference;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private UUID invoiceId;
    private String paymentGateway;
    private String message;
}
