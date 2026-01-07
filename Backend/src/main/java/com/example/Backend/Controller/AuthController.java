package com.example.Backend.Controller;

import com.example.Backend.Dto.Login.LoginRequest;
import com.example.Backend.Dto.Login.LoginResponse;
import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;
import com.example.Backend.Service.AuthService;
import com.example.Backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse addNewUser(@RequestBody RegisterUserRequest addUserRequest){
        return authService.createNewUser(addUserRequest);
    }

    @PostMapping("/login")
    public LoginResponse verifyUser(@RequestBody LoginRequest loginRequest){
        return authService.verifyUser(loginRequest);
    }
}
