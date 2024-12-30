package com.finpay.payment_service.services;

import com.finpay.payment_service.dtos.*;
import com.finpay.payment_service.gateways.PaymentGateway;
import com.finpay.payment_service.gateways.PaymentGatewayFactory;
import com.finpay.payment_service.models.Payment;
import com.finpay.payment_service.models.PaymentStatus;
import com.finpay.payment_service.models.Refund;
import com.finpay.payment_service.models.RefundStatus;
import com.finpay.payment_service.repository.PaymentRepository;
import com.finpay.payment_service.repository.RefundRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RefundRepository refundRepository;
    //private final InvoiceServiceClient invoiceServiceClient;
    private final PaymentGatewayFactory paymentGatewayFactory;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              RefundRepository refundRepository,
//                              InvoiceServiceClient invoiceServiceClient,
                              PaymentGatewayFactory paymentGatewayFactory) {
        this.paymentRepository = paymentRepository;
        this.refundRepository = refundRepository;
//        this.invoiceServiceClient = invoiceServiceClient;
        this.paymentGatewayFactory = paymentGatewayFactory;
    }

    @Override
    @Transactional
    public PaymentResponse initiatePayment(PaymentRequest paymentRequest) {
        // Validate Invoice
//        InvoiceResponse invoice = invoiceServiceClient.getInvoiceById(paymentRequest.getInvoiceId());
//        if (invoice == null || !"PAID".equalsIgnoreCase(invoice.getStatus())) {
//            throw new IllegalArgumentException("Invalid or unpaid invoice.");
//        }

        // Create Payment entity
        Payment payment = Payment.builder()
                .paymentReference(UUID.randomUUID().toString())
                .amount(paymentRequest.getAmount())
                .currency(paymentRequest.getCurrency())
                .status(PaymentStatus.PENDING)
                .invoiceId(paymentRequest.getInvoiceId())
                .paymentGateway(paymentRequest.getPaymentGateway())
                .createdAt(LocalDateTime.now())
                .build();

        // Save Payment
        paymentRepository.save(payment);

        // Get the appropriate PaymentGateway
        PaymentGateway gateway = paymentGatewayFactory.getPaymentGateway(paymentRequest);

        // Process Payment via Gateway
        PaymentGatewayResponse gatewayResponse = gateway.processPayment(paymentRequest);

        // Update Payment Status based on Gateway Response
        if ("SUCCESS".equalsIgnoreCase(gatewayResponse.getStatus())) {
            payment.setStatus(PaymentStatus.COMPLETED);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }
        payment.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        // Return Response
        return new PaymentResponse(
                payment.getId(),
                payment.getPaymentReference(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getInvoiceId(),
                payment.getPaymentGateway(),
                gatewayResponse.getMessage()
        );
    }

    @Override
    public Optional<PaymentResponse> getPaymentByReference(String paymentReference) {
        return paymentRepository.findByPaymentReference(paymentReference)
                .map(payment -> new PaymentResponse(
                        payment.getId(),
                        payment.getPaymentReference(),
                        payment.getAmount(),
                        payment.getCurrency(),
                        payment.getStatus(),
                        payment.getInvoiceId(),
                        payment.getPaymentGateway(),
                        "Payment retrieved successfully."
                ));
    }

    @Override
    @Transactional
    public RefundResponse processRefund(RefundRequest refundRequest) {
        // Validate Payment
        Optional<Payment> paymentOpt = paymentRepository.findById(refundRequest.getPaymentId());
        if (paymentOpt.isEmpty()) {
            throw new IllegalArgumentException("Payment not found.");
        }

        Payment payment = paymentOpt.get();
        if (payment.getStatus() != PaymentStatus.COMPLETED) {
            throw new IllegalStateException("Only completed payments can be refunded.");
        }

        // Create Refund entity
        Refund refund = Refund.builder()
                .refundReference(UUID.randomUUID().toString())
                .status(RefundStatus.INITIATED)
                .amount(refundRequest.getAmount())
                .payment(payment)
                .reason(refundRequest.getReason())
                .createdAt(LocalDateTime.now())
                .build();

        // Save Refund
        refundRepository.save(refund);

        // Get the appropriate PaymentGateway
        //PaymentRequest dummyPaymentRequest = new PaymentRequest(); // Not used here
        PaymentGateway gateway = paymentGatewayFactory.getPaymentGateway(
                PaymentRequest.builder()
                        .paymentMethod(payment.getPaymentGateway())
                        .build()
        );

        // Process Refund via Gateway
        RefundGatewayResponse gatewayResponse = gateway.processRefund(refundRequest);

        // Update Refund Status based on Gateway Response
        if ("SUCCESS".equalsIgnoreCase(gatewayResponse.getStatus())) {
            refund.setStatus(RefundStatus.COMPLETED);
            payment.setStatus(PaymentStatus.REFUNDED);
        } else {
            refund.setStatus(RefundStatus.FAILED);
        }
        refund.setUpdatedAt(LocalDateTime.now());
        refundRepository.save(refund);
        paymentRepository.save(payment);

        // Return Response
        return new RefundResponse(
                refund.getId(),
                refund.getRefundReference(),
                refund.getStatus(),
                refund.getAmount(),
                refund.getReason(),
                refund.getCreatedAt()
        );
    }
}
