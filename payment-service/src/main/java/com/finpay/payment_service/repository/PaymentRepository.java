package com.finpay.payment_service.repository;

import com.finpay.payment_service.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByPaymentReference(String paymentReference);
}
