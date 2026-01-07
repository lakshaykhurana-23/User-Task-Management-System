package com.example.Backend.Dto.Task;

import com.example.Backend.Model.Entity.User;
import com.example.Backend.Model.Enum.Priority;
import com.example.Backend.Model.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {

    private String title;

    private String description;

    private Priority priority;

    private Long assignedToUserId;

    private Status status = Status.TODO;
}
