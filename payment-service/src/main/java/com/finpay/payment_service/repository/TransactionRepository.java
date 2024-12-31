package com.finpay.payment_service.repository;

import com.finpay.payment_service.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByTransactionReference(String transactionReference);
}
