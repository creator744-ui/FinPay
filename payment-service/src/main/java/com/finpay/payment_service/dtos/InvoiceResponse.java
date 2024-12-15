package com.finpay.payment_service.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class InvoiceResponse {
    private UUID id;
    private UUID userId;
    private BigDecimal totalAmount;
    private String currency;
    private String status;
    // Add other relevant fields as necessary
}
