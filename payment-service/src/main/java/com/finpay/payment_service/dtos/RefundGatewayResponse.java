package com.finpay.payment_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundGatewayResponse {
    /**
     * Status of the refund operation.
     * Possible values: SUCCESS, FAILURE
     */
    private String status;

    /**
     * Message providing additional information about the refund operation.
     */
    private String message;

    /**
     * Unique identifier for the refund, provided by the payment gateway.
     * Example: A refund transaction ID or reference number.
     */
    private String refundId;

    /**
     * Additional details or metadata about the refund (optional).
     */
    private String additionalDetails;
}
