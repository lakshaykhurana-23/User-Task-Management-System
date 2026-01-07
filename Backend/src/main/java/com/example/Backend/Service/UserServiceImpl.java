package com.example.Backend.Service;

import com.example.Backend.Dto.User.RegisterUserRequest;
import com.example.Backend.Dto.User.UserResponse;
import com.example.Backend.Model.Entity.User;
import com.example.Backend.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userrepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserResponse> getAllUsers(){

        List<User> users = userrepository.findAll();
        return users
                .stream()
                .map(user -> modelMapper.map(user,UserResponse.class))
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userrepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not found with id "+id));
        return modelMapper.map(user , UserResponse.class);
    }

    @Override
    public void deleteById(Long id) {
        if(!userrepository.existsById(id)){
            throw new IllegalArgumentException("user not found with id"+id);
        }
        userrepository.deleteById(id);
    }

    @Override
    public UserResponse updateUserById(Long id , RegisterUserRequest addUserRequest) {
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
