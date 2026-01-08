package com.example.Backend.Service;

import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers(String authorizationHeader);
    UserResponse getUserById(Long id , String authorizationHeader);

    void deleteById(Long id , String authorizationHeader);

    UserResponse updateUserById(String authorizationHeader , Long id , RegisterUserRequest addUserRequest);
}
