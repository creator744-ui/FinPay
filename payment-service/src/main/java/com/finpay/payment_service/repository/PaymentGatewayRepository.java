package com.finpay.payment_service.repository;

import com.finpay.payment_service.models.PaymentGateway;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PaymentGatewayRepository extends JpaRepository<PaymentGateway, UUID> {
    Optional<PaymentGateway> findByName(String name);
}
