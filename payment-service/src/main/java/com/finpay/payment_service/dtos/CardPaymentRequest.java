package com.finpay.payment_service.dtos;
import lombok.Data;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
@Data
public class CardPaymentRequest {

//    @NotBlank(message = "Card number is required")
//    @Pattern(regexp = "^[0-9]{13,19}$", message = "Invalid card number")

    private String cardNumber;

//    @NotBlank(message = "Expiry month is required")
//    @Pattern(regexp = "^(0[1-9]|1[0-2])$", message = "Invalid expiry month")
    private String expiryMonth;

//    @NotBlank(message = "Expiry year is required")
//    @Pattern(regexp = "^[0-9]{2}$", message = "Invalid expiry year")
    private String expiryYear;

//    @NotBlank(message = "CVV is required")
//    @Pattern(regexp = "^[0-9]{3,4}$", message = "Invalid CVV")
    private String cvv;

//    @NotBlank(message = "Cardholder name is required")
    private String cardHolderName;

//    @NotBlank(message = "Billing address is required")
    private String billingAddress;

}
