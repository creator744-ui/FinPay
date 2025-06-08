package com.finpay.authentication.services;
import com.finpay.authentication.clients.UserServiceClient;
import com.finpay.authentication.dtos.UserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceClient userServiceClient; // REST or gRPC client to fetch user data

    public UserDetailsServiceImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = userServiceClient.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        List<SimpleGrantedAuthority> authorities = userDto.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new org.springframework.security.core.userdetails.User(
                userDto.getEmail(),
                userDto.getPassword(),
                authorities
        );
    }
}
