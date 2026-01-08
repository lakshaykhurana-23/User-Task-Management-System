package com.example.Backend.Dto;

import com.example.Backend.Model.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticatedUser {

    private Long id;
    private String email;
    private Role role;
}
