package com.finpay.authentication.controllers;

import com.finpay.authentication.clients.UserServiceClient;
import com.finpay.authentication.dtos.*;
import com.finpay.authentication.utils.JwtUtil;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserServiceClient userServiceClient; // Add UserServiceClient

    // Constructor-based injection
    public AuthController(AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil,
                          UserServiceClient userServiceClient) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userServiceClient = userServiceClient; // Initialize UserServiceClient
    }

    @PostMapping("/login")
      public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Fetch user details from User Service
            UserDto userDto = userServiceClient.getUserByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Generate JWT with email and roles
            String jwt = jwtUtil.generateJwtToken(loginRequest.getEmail(), userDto.getRoles());

            // Build JWT response
            JwtResponse jwtResponse = new JwtResponse(jwt, userDto.getId(), userDto.getName(), userDto.getEmail());
            return ResponseEntity.ok(jwtResponse);

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Error: Invalid email or password"));
        }
    }


}
