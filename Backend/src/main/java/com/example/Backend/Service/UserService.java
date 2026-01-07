package com.example.Backend.Service;

import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);

    void deleteById(Long id);

    UserResponse updateUserById(Long id , RegisterUserRequest addUserRequest);
}
