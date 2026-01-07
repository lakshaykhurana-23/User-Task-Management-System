package com.example.Backend.Repository;

import com.example.Backend.Model.Entity.Task;
import com.example.Backend.Model.Enum.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task , Long> {
    List<Task> findByAssignedToUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.status = :newstatus where t.id = :id")
    void updateStatusOfTask(@Param("id") Long id,@Param("newstatus") Status newstatus);

    List<Task> findByStatus(Status status);
}
