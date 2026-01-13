package com.example.Backend.Dto.Login;

import com.example.Backend.Model.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginResponse {
    private String jwtToken;
    private Role role;
    private Long expiresIn;
}
