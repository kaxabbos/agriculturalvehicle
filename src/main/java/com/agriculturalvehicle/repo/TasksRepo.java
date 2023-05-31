package com.agriculturalvehicle.repo;

import com.agriculturalvehicle.model.Tasks;
import com.agriculturalvehicle.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<Tasks, Long> {
    List<Tasks> findAllByStatus(TaskStatus status);

}
