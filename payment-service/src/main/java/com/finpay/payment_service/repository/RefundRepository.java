package com.finpay.payment_service.repository;

import com.finpay.payment_service.models.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface RefundRepository extends JpaRepository<Refund, UUID> {
    Optional<Refund> findByRefundReference(String refundReference);
}
