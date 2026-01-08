package com.example.Backend.Service;

import com.example.Backend.Dto.AuthenticatedUser;
import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;
import com.example.Backend.Model.Entity.User;
import com.example.Backend.Model.Enum.Role;
import com.example.Backend.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userrepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;


    @Override
    public List<UserResponse> getAllUsers(String authorizationHeader){
        System.out.println("entered the function");
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!authenticatedUser.getRole().equals(Role.ADMIN)) throw new IllegalArgumentException("Unauthorised access");
        List<User> users = userrepository.findAll();
        return users
                .stream()
                .map(user -> modelMapper.map(user,UserResponse.class))
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id ,String authorizationHeader) {
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!authenticatedUser.getRole().equals(Role.ADMIN)) throw new IllegalArgumentException("Unauthorised access");
        User user = userrepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not found with id "+id));
        return modelMapper.map(user , UserResponse.class);
    }

    @Override
    public void deleteById(Long id , String authorizationHeader) {
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!authenticatedUser.getRole().equals(Role.ADMIN)) throw new IllegalArgumentException("Unauthorised access");
        if(!userrepository.existsById(id)){
            throw new IllegalArgumentException("user not found with id"+id);
        }
        userrepository.deleteById(id);
    }

    @Override
    public UserResponse updateUserById(String authorizationHeader ,Long id , RegisterUserRequest addUserRequest) {
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!authenticatedUser.getRole().equals(Role.ADMIN)) throw new IllegalArgumentException("Unauthorised access");
        User user = userrepository.findById(id).orElseThrow(()->new IllegalArgumentException("user not found with id"+id));
        user.setRole(addUserRequest.getRole());
        user.setName(addUserRequest.getName());
        user.setEmail(addUserRequest.getEmail());
        user.setPassword(addUserRequest.getPassword());
        userrepository.save(user);
        return modelMapper.map(user,UserResponse.class);
    }


    ;
}
