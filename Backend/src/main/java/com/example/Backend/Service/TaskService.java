package com.example.Backend.Service;

import com.example.Backend.Dto.Task.AddTaskRequest;
import com.example.Backend.Dto.Task.TaskResponse;
import com.example.Backend.Model.Enum.Status;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface
TaskService {
    List<TaskResponse> getAllTasks(Status status ,String authorizationHeader);

    TaskResponse addNewTask(String authorizationHeader,AddTaskRequest addTaskRequest);

    TaskResponse getTaskById(Long id, String authorizationHeader);

    void deleteById(Long id , String authorizationHeader);

    TaskResponse updateTaskById(Long id , AddTaskRequest addTaskRequest , String authorizationHeader);

    List<TaskResponse> getAllTasksOfAUser(String authorizationHeader);

    void updateStatusofTaskById(Long id, Status newstatus , String authorizationHeader);

}
