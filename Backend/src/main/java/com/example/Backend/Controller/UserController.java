package com.example.Backend.Controller;

import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;
import com.example.Backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponse> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUserById(@PathVariable Long id , @RequestBody RegisterUserRequest addUserRequest ){
        return userService.updateUserById(id , addUserRequest);
    }
}
