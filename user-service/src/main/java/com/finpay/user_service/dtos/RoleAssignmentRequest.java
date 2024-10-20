package com.finpay.user_service.dtos;
import com.finpay.user_service.models.Role;
import lombok.Data;

import java.util.Set;
@Data
public class RoleAssignmentRequest {
    private Set<Role> roles;
}
