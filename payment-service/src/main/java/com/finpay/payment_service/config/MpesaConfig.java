package com.finpay.payment_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mpesa")
@Data
public class MpesaConfig {

    private String consumerKey;
    private String consumerSecret;
    private String passkey;
    private String businessShortCode;
    private String initiatorName;
    private String initiatorPassword;
    private String callbackUrl;
    private String registerUrl;

}
