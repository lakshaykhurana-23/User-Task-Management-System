package com.example.Backend.Service;
import com.example.Backend.Dto.AuthenticatedUser;
import com.example.Backend.Dto.Login.LoginRequest;
import com.example.Backend.Dto.Login.LoginResponse;
import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;
import com.example.Backend.Model.Entity.User;
import com.example.Backend.Model.Enum.Role;
import com.example.Backend.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @Value("${AdminPassword}")
    private String AdminPassword;

    @Value("${ManagerPassword}")
    private String ManagerPassword;

    public AuthenticatedUser authorize(String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing token");
        }

        String token = authHeader.substring(7);

        if (!jwtService.isTokenValid(token)) {
            throw new RuntimeException("Invalid or expired token");
        }

        return AuthenticatedUser.builder()
                .id(jwtService.extractUserId(token))
                .email(jwtService.extractEmail(token))
                .role(jwtService.extractRole(token))
                .build();
    }

    public UserResponse createNewUser(RegisterUserRequest addUserRequest) {
        if(addUserRequest.getRole().equals(Role.ADMIN)){
            System.out.println(addUserRequest.getPassword());
            if(!(addUserRequest.getPassword().equals(AdminPassword))) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if(addUserRequest.getRole().equals(Role.MANAGER)){
            if(!addUserRequest.getPassword().equals(ManagerPassword)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        User user  = modelMapper.map(addUserRequest , User.class);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
    }

    public LoginResponse verifyUser(LoginRequest loginRequest) {
        if(!userRepository.existsByEmail(loginRequest.getEmail())) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        System.out.println(loginRequest.getEmail());
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(!Objects.equals(user.getPassword(), loginRequest.getPassword())) throw new IllegalArgumentException("Wrong password");

        String jwt = jwtService.generateToken(user.getRole() , user.getId() , user.getEmail());

        return LoginResponse.builder()
                .jwtToken(jwt)
                .expiresIn(86400000L)
                .build();
    }

//    public void logoutUser(String authorizationHeader) {
//        AuthenticatedUser authenticatedUser = authorize(authorizationHeader);
//
//
//    }
}
