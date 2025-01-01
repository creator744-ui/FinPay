//package com.finpay.payment_service.gateways.mpesa;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.finpay.payment_service.dtos.PaymentGatewayResponse;
//import com.finpay.payment_service.dtos.RefundGatewayResponse;
//import com.finpay.payment_service.gateways.PaymentGateway;
//import com.finpay.payment_service.config.MpesaConfig;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDateTime;
//import java.util.Base64;
//
//@Component
//@RequiredArgsConstructor
//public class MpesaPaymentGateway implements PaymentGateway {
//
//    private final MpesaConfig mpesaConfig;
//    private final RestTemplate restTemplate;
//    private final ObjectMapper objectMapper;
//    /**
//     * Processes a payment through M-Pesa STK-PUSH.
//     *
//     * @param paymentRequest The payment details.
//     * @return The response from M-Pesa.
//     */
//    @Override
//    public PaymentGatewayResponse processPayment(com.finpay.payment_service.dtos.PaymentRequest paymentRequest) {
//        PaymentGatewayResponse response = new PaymentGatewayResponse();
//
//        try {
//            String accessToken = getAccessToken();
//
//            String url = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
//
//            String timestamp = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//            String password = Base64.getEncoder().encodeToString(
//                    (mpesaConfig.getBusinessShortCode() + mpesaConfig.getPasskey() + timestamp).getBytes()
//            );
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setBearerAuth(accessToken);
//
//            JsonNode payload = objectMapper.createObjectNode()
//                    .put("BusinessShortCode", mpesaConfig.getBusinessShortCode())
//                    .put("Password", password)
//                    .put("Timestamp", timestamp)
//                    .put("TransactionType", "CustomerPayBillOnline")
//                    .put("Amount", paymentRequest.getAmount())
//                    .put("PartyA", paymentRequest.getPhoneNumber())
//                    .put("PartyB", mpesaConfig.getBusinessShortCode())
//                    .put("PhoneNumber", paymentRequest.getPhoneNumber())
//                    .put("CallBackURL", mpesaConfig.getCallbackUrl())
//                    .put("AccountReference", paymentRequest.getAccountReference())
//                    .put("TransactionDesc", paymentRequest.getTransactionDesc());
//
//            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(payload), headers);
//
//            ResponseEntity<String> apiResponse = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//
//            if (apiResponse.getStatusCode() == HttpStatus.OK) {
//                JsonNode responseBody = objectMapper.readTree(apiResponse.getBody());
//
//                String merchantRequestID = responseBody.get("MerchantRequestID").asText();
//                String checkoutRequestID = responseBody.get("CheckoutRequestID").asText();
//                String responseCode = responseBody.get("ResponseCode").asText();
//                String responseDescription = responseBody.get("ResponseDescription").asText();
//                String customerMessage = responseBody.get("CustomerMessage").asText();
//
//                // Save transaction details
//                MpesaTransaction transaction = MpesaTransaction.builder()
//                        .merchantRequestID(merchantRequestID)
//                        .checkoutRequestID(checkoutRequestID)
//                        .amount(paymentRequest.getAmount())
//                        .phoneNumber(paymentRequest.getPhoneNumber())
//                        .status("PENDING")
//                        .createdAt(LocalDateTime.now())
//                        .build();
//
//                mpesaTransactionRepository.save(transaction);
//
//                response.setStatus("SUCCESS");
//                response.setMessage("STK-PUSH initiated successfully.");
//                response.setPaymentReference(checkoutRequestID);
//            } else {
//                response.setStatus("FAILURE");
//                response.setMessage("Failed to initiate STK-PUSH.");
//            }
//        } catch (Exception e) {
//            response.setStatus("FAILURE");
//            response.setMessage("Error processing M-Pesa payment: " + e.getMessage());
//            // Optionally, log the error using a logger
//        }
//
//        return response;
//    }
//
//    /**
//     * Processes a refund through M-Pesa.
//     *
//     * @param refundRequest The refund details.
//     * @return The response from M-Pesa.
//     */
//    @Override
//    public RefundGatewayResponse processRefund(com.finpay.payment_service.dtos.RefundRequest refundRequest) {
//        RefundGatewayResponse response = new RefundGatewayResponse();
//
//        // Implement refund logic if M-Pesa supports it. Currently, M-Pesa's C2B API does not support refunds.
//        // This is a placeholder for future implementation.
//
//        response.setStatus("FAILED");
//        response.setMessage("Refunds are not supported through M-Pesa STK-PUSH.");
//        return response;
//    }
//
//    /**
//     * Retrieves the current access token, fetching a new one if necessary.
//     *
//     * @return The access token.
//     */
//    private String getAccessToken() {
//        // Implement caching logic with Redis or similar if desired
//        String accessToken = fetchAccessToken();
//        return accessToken;
//    }
//
//    /**
//     * Fetches a new access token from M-Pesa.
//     *
//     * @return The new access token.
//     */
//    private String fetchAccessToken() {
//        try {
//            String url = "https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials";
//
//            String credentials = mpesaConfig.getConsumerKey() + ":" + mpesaConfig.getConsumerSecret();
//            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Basic " + encodedCredentials);
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<String> entity = new HttpEntity<>(null, headers);
//
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//
//            if (response.getStatusCode() == HttpStatus.OK) {
//                JsonNode node = objectMapper.readTree(response.getBody());
//                return node.get("access_token").asText();
//            } else {
//                throw new RuntimeException("Failed to fetch access token from M-Pesa");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error fetching access token: " + e.getMessage(), e);
//        }
//    }
//}
