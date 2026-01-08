package com.example.Backend.Service;

import com.example.Backend.Dto.AuthenticatedUser;
import com.example.Backend.Dto.Task.AddTaskRequest;
import com.example.Backend.Dto.Task.TaskResponse;
import com.example.Backend.Model.Entity.Task;
import com.example.Backend.Model.Entity.User;
import com.example.Backend.Model.Enum.Role;
import com.example.Backend.Model.Enum.Status;
import com.example.Backend.Repository.TaskRepository;
import com.example.Backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    public List<TaskResponse> getAllTasks(Status status , String authorizationHeader) {
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!((authenticatedUser.getRole().equals(Role.ADMIN)) || (authenticatedUser.getRole().equals(Role.MANAGER)))) throw new IllegalArgumentException("Unauthorised access");
        List<Task> tasks ;
        if(status == null) tasks = taskRepository.findAll();
        else tasks = taskRepository.findByStatus(status);
        return tasks
                .stream()
                .map(task -> modelMapper.map(task , TaskResponse.class))
                .toList();
    }

    @Override
    public TaskResponse addNewTask(String authorizationHeader, AddTaskRequest addTaskRequest) {
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!authenticatedUser.getRole().equals(Role.ADMIN)) throw new IllegalArgumentException("Unauthorised access");
        User user = userRepository.findById(addTaskRequest.getAssignedToUserId()).orElseThrow(()-> new IllegalArgumentException("not found"));

        Task task = Task.builder()
                .title(addTaskRequest.getTitle())
                .description(addTaskRequest.getDescription())
                .priority(addTaskRequest.getPriority())
                .assignedToUser(user)
                .status(addTaskRequest.getStatus())
                .build();
        taskRepository.save(task);
        return modelMapper.map(task , TaskResponse.class);
    }

    @Override
    public TaskResponse getTaskById(Long id , String authorizationHeader) {
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!((authenticatedUser.getRole().equals(Role.ADMIN)) || (authenticatedUser.getRole().equals(Role.MANAGER)))) throw new IllegalArgumentException("Unauthorised access");
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found with id "+id));
        return modelMapper.map(task , TaskResponse.class);
    }

    @Override
    public void deleteById(Long id , String authorizationHeader ){
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!authenticatedUser.getRole().equals(Role.ADMIN)) throw new IllegalArgumentException("Unauthorised access");
        if(!taskRepository.existsById(id)){
            throw new IllegalArgumentException("Task not found with id "+id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponse updateTaskById(Long id , AddTaskRequest addTaskRequest , String authorizationHeader) {
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!authenticatedUser.getRole().equals(Role.ADMIN)) throw new IllegalArgumentException("Unauthorised access");
        User user = userRepository.findById(addTaskRequest.getAssignedToUserId()).orElseThrow(()-> new IllegalArgumentException("User not found"));
        Task task = taskRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Task not found with id"+id));
        task.setTitle(addTaskRequest.getTitle());
        task.setDescription(addTaskRequest.getDescription());
        task.setStatus(addTaskRequest.getStatus());
        task.setPriority(addTaskRequest.getPriority());
        task.setAssignedToUser(user);
        taskRepository.save(task);
        return modelMapper.map(task,TaskResponse.class);
    }

    @Override
    public List<TaskResponse> getAllTasksOfAUser(String authorizationHeader) {
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        Long userId = authenticatedUser.getId();
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User not found with id "+userId));
        List<Task> tasks = taskRepository.findByAssignedToUserId(userId);
        return tasks.stream().map(task -> modelMapper.map(task,TaskResponse.class)).toList();
    }

    @Override
    public void updateStatusofTaskById(Long id, Status newstatus , String authorizationHeader) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        AuthenticatedUser authenticatedUser = authService.authorize(authorizationHeader);
        if(!((authenticatedUser.getRole().equals(Role.ADMIN) || (authenticatedUser.getId().equals(task.getAssignedToUser().getId()))))) {
            throw new IllegalArgumentException("Unauthorised access");
        }
//        if(!taskRepository.existsById(id)){
//            throw new IllegalArgumentException("Task not found with id "+id);
//        }
        taskRepository.updateStatusOfTask(id,newstatus);
    }

}
