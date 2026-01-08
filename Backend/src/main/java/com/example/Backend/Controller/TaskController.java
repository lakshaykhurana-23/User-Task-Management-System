package com.example.Backend.Controller;

import com.example.Backend.Dto.Task.AddTaskRequest;
import com.example.Backend.Dto.Task.TaskResponse;
import com.example.Backend.Model.Enum.Status;
import com.example.Backend.Service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskResponse> getAllTasks(@RequestParam(required = false) Status status , @RequestHeader("Authorization") String authorizationHeader){
        return taskService.getAllTasks(status ,authorizationHeader);
    }

    @PostMapping("/tasks")
    public TaskResponse addNewTask(@RequestHeader("Authorization") String authorizationHeader,@RequestBody AddTaskRequest addTaskRequest){
        return taskService.addNewTask(authorizationHeader,addTaskRequest);
    }

    @GetMapping("/tasks/{id}")
    public TaskResponse getTaskById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long id){
        return taskService.getTaskById(id,authorizationHeader);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long id){
        taskService.deleteById(id , authorizationHeader);
    }

    @PutMapping("/tasks/{id}")
    public TaskResponse updateTaskByid(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long id , @RequestBody AddTaskRequest addTaskRequest){
        return taskService.updateTaskById(id,addTaskRequest,authorizationHeader);
    }

    @GetMapping("/tasks/my/")
    public List<TaskResponse> getTasksByUserId(@RequestHeader("Authorization") String authorizationHeader){
        return taskService.getAllTasksOfAUser(authorizationHeader);
    }

    @PatchMapping("/tasks/{id}/status")
    public void updateStatusOfTask(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long id , @RequestParam Status newstatus){
        taskService.updateStatusofTaskById(id,newstatus,authorizationHeader);
    }





}
