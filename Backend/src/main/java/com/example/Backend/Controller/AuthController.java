package com.example.Backend.Controller;

import com.example.Backend.Dto.Login.LoginRequest;
import com.example.Backend.Dto.Login.LoginResponse;
import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;
import com.example.Backend.Model.Enum.Role;
import com.example.Backend.Service.AuthService;
import com.example.Backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS }
)
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

//    @GetMapping("/logout")
//    public void logout(@RequestHeader("Authorization") String authorizationHeader){
//        authService.logoutUser(authorizationHeader);
//    }
}

