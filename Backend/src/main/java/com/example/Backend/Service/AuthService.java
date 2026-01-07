package com.example.Backend.Service;
import com.example.Backend.Dto.Login.LoginRequest;
import com.example.Backend.Dto.Login.LoginResponse;
import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;
import com.example.Backend.Model.Entity.User;
import com.example.Backend.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponse createNewUser(RegisterUserRequest addUserRequest) {
        User user  = modelMapper.map(addUserRequest , User.class);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return modelMapper.map(addUserRequest,UserResponse.class);
    }

    public LoginResponse verifyUser(LoginRequest loginRequest) {
        if(!userRepository.existsByEmail(loginRequest.getEmail())) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(!Objects.equals(user.getPassword(), loginRequest.getPassword())) throw new IllegalArgumentException("Wrong password");

        String jwt = Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt()
                .setExpiration(Date.from(expiration)) // When the token expires
                .claim("role", "user") // A custom claim
                .signWith(signingKey) // Signs the JWT
                .compact(); // Builds and serializes the JWT to a string

        return jwt;
    }
}
