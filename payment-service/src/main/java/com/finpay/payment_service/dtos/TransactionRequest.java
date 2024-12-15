package com.finpay.payment_service.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    private String transactionType;
    private BigDecimal amount;
}
