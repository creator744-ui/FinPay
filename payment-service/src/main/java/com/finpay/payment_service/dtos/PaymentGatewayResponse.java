package com.finpay.payment_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentGatewayResponse {
    /**
     * Status of the payment operation.
     * Possible values: SUCCESS, FAILURE
     */
    private String status;

    /**
     * Message providing additional information about the payment operation.
     */
    private String message;

    /**
     * Unique identifier for the payment, provided by the payment gateway.
     * Example: A transaction ID or reference number.
     */
    private String paymentId;

    /**
     * Additional details or metadata about the payment (optional).
     */
    private String additionalDetails;
}
