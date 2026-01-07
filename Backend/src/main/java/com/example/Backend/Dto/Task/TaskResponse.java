package com.example.Backend.Dto.Task;

import com.example.Backend.Model.Entity.User;
import com.example.Backend.Model.Enum.Priority;
import com.example.Backend.Model.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private User assignedTo;
}
