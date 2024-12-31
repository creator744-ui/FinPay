package com.finpay.authentication.clients;
import com.finpay.authentication.dtos.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Component
public class UserServiceClient {

    private final RestTemplate restTemplate;
    private final String apiGatewayBaseUrl = "http://api-gateway"; // API Gateway base URL
    private final String userServicePath = "/user-service/api/users"; // Path to User Service in the gateway

    public UserServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetch a user by email from the User Service via the API Gateway.
     *
     * @param email the email of the user
     * @return an Optional containing UserDto if the user exists, or empty if not found
     */
    public Optional<UserDto> getUserByEmail(String email) {
        String url = UriComponentsBuilder.fromHttpUrl(apiGatewayBaseUrl + userServicePath)
                .queryParam("email", email)
                .toUriString();
        try {
            UserDto userDto = restTemplate.getForObject(url, UserDto.class);
            return Optional.ofNullable(userDto);
        } catch (Exception e) {
            // Log error or handle exception as needed
            return Optional.empty();
        }
    }
}
