package com.finpay.payment_service.gateways;

import com.finpay.payment_service.dtos.PaymentRequest;
import com.finpay.payment_service.dtos.PaymentGatewayResponse;
import com.finpay.payment_service.dtos.RefundRequest;
import com.finpay.payment_service.dtos.RefundGatewayResponse;

public interface PaymentGateway {

    /**
     * Processes a payment through the specific gateway.
     *
     * @param paymentRequest The payment details.
     * @return The response from the payment gateway.
     */
    PaymentGatewayResponse processPayment(PaymentRequest paymentRequest);

    /**
     * Processes a refund through the specific gateway.
     *
     * @param refundRequest The refund details.
     * @return The response from the refund gateway.
     */
    RefundGatewayResponse processRefund(RefundRequest refundRequest);
}
