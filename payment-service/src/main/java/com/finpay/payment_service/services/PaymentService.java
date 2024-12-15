package com.finpay.payment_service.services;

import com.finpay.payment_service.dtos.PaymentRequest;
import com.finpay.payment_service.dtos.PaymentResponse;
import com.finpay.payment_service.dtos.RefundRequest;
import com.finpay.payment_service.dtos.RefundResponse;

import java.util.Optional;
public interface PaymentService {
    PaymentResponse initiatePayment(PaymentRequest paymentRequest);
    Optional<PaymentResponse> getPaymentByReference(String paymentReference);
    RefundResponse processRefund(RefundRequest refundRequest);

}
