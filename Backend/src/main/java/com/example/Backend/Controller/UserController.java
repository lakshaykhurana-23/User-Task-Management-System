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
    public List<UserResponse> getUsers(@RequestHeader("Authorization") String authorizationHeader){
        System.out.println("api call");
        return userService.getAllUsers(authorizationHeader);
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@RequestHeader("Authorization") String authorizationHeader ,@PathVariable Long id){
        return userService.getUserById(id,authorizationHeader);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id , @RequestHeader("Authorization") String authorizationHeader){
        userService.deleteById(id , authorizationHeader);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUserById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long id , @RequestBody RegisterUserRequest addUserRequest ){
        return userService.updateUserById(authorizationHeader,id , addUserRequest);
    }
}
