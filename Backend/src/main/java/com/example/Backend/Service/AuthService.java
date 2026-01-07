package com.example.Backend.Service;
import com.example.Backend.Dto.Login.LoginRequest;
import com.example.Backend.Dto.Login.LoginResponse;
import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;
import com.example.Backend.Model.Entity.User;
import com.example.Backend.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

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

    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public UserResponse createNewUser(RegisterUserRequest addUserRequest) {
        User user  = modelMapper.map(addUserRequest , User.class);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return modelMapper.map(addUserRequest,UserResponse.class);
    }

    public LoginResponse verifyUser(LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail());
        System.out.println(loginRequest.getPassword());
        if(!userRepository.existsByEmail(loginRequest.getEmail())) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        System.out.println(loginRequest.getEmail());
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(!Objects.equals(user.getPassword(), loginRequest.getPassword())) throw new IllegalArgumentException("Wrong password");

        String jwt = Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .claim("userId",user.getId())
                .signWith(getSigningKey())
                .compact();

        LoginResponse loginResponse = LoginResponse.builder()
                .jwtToken(jwt)
                .expiresIn(86400000L)
                .build();
        return loginResponse;
    }
}
