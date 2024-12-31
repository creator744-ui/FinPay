package com.finpay.payment_service.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "payment_gateways")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentGateway {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name; // e.g., Stripe, PayPal, Square

    @Column(nullable = false)
    private String apiEndpoint;

    @Column(nullable = false)
    private String apiKey;

    @OneToMany(mappedBy = "paymentGateway", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments;
}
