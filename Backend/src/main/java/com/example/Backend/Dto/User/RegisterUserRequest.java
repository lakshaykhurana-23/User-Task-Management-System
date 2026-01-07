package com.example.Backend.Dto.User;

import com.example.Backend.Model.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    private String name;

    private String email;

    private String password;

    private Role role;

}
