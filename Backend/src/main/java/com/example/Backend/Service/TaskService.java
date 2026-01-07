package com.example.Backend.Service;

import com.example.Backend.Dto.Task.AddTaskRequest;
import com.example.Backend.Dto.Task.TaskResponse;
import com.example.Backend.Model.Enum.Status;

import java.util.List;

public interface
TaskService {
    List<TaskResponse> getAllTasks();

    TaskResponse addNewTask(AddTaskRequest addTaskRequest);

    TaskResponse getTaskById(Long id);

    void deleteById(Long id);

    TaskResponse updateTaskById(Long id , AddTaskRequest addTaskRequest);

    List<TaskResponse> getAllTasksOfAUser(Long userId);

    void updateStatusofTaskById(Long id, Status newstatus);

    List<TaskResponse> getAllTasksByStatus(Status status);
}
