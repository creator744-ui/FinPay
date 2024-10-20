package com.finpay.user_service.controllers;

import com.finpay.user_service.dtos.RoleAssignmentRequest;
import com.finpay.user_service.dtos.UserRegistrationRequest;
import com.finpay.user_service.dtos.UserResponse;
import com.finpay.user_service.models.Role;
import com.finpay.user_service.models.User;
import com.finpay.user_service.services.UserService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    // Constructor-based injection
    public UserController(UserService userService) {
        this.userService = userService;

    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        if (userService.existsByEmail(registrationRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: Email is already in use!");
        }

        // Assign default role
        Set<Role> defaultRoles = Set.of(Role.ROLE_USER);

        User user = User.builder()
                .id(UUID.randomUUID())
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .roles(defaultRoles)
                .build();

        User savedUser = userService.save(user);

        UserResponse userResponse = new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> userOpt = userService.findByEmail(email);

        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        User user = userOpt.get();
        UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getEmail());

        return ResponseEntity.ok(userResponse);
    }

    // Endpoint to assign roles to a user (Restricted to ADMIN)
    @PostMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignRolesToUser(@PathVariable UUID id, @RequestBody RoleAssignmentRequest roleAssignmentRequest) {
        Optional<User> userOpt = userService.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        User user = userOpt.get();
        Set<Role> roles = roleAssignmentRequest.getRoles();

        userService.assignRoles(user, roles);

        return ResponseEntity.ok("Roles assigned successfully.");
    }

}
