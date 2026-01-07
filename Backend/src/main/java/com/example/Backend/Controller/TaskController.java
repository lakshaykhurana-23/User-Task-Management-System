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
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public TaskResponse addNewTask(@RequestBody AddTaskRequest addTaskRequest){
        return taskService.addNewTask(addTaskRequest);
    }

    @GetMapping("/tasks/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable Long id){
        taskService.deleteById(id);
    }

    @PutMapping("/tasks/{id}")
    public TaskResponse updateTaskByid(@PathVariable Long id , @RequestBody AddTaskRequest addTaskRequest){
        return taskService.updateTaskById(id,addTaskRequest);
    }

    @GetMapping("/tasks/my/{user_id}")
    public List<TaskResponse> getTasksByUserId(@PathVariable Long user_id){
        return taskService.getAllTasksOfAUser(user_id);
    }

    @PatchMapping("/tasks/{id}/status")
    public void updateStatusOfTask(@PathVariable Long id , @RequestParam Status newstatus){
        taskService.updateStatusofTaskById(id,newstatus);
    }

    @GetMapping("/tasks/status")
    public List<TaskResponse> getTasksByStatus(@RequestParam Status status){
        return taskService.getAllTasksByStatus(status);
    }





}
