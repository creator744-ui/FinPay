package com.finpay.payment_service.repository;

import com.finpay.payment_service.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
    Optional<PaymentMethod> findByType(String type);
}
