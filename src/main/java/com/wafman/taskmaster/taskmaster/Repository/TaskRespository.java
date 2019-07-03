package com.wafman.taskmaster.taskmaster.Repository;

import com.wafman.taskmaster.taskmaster.Model.Task;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@EnableScan
public interface TaskRespository extends CrudRepository<Task, UUID> {
    Optional<Task> findById(UUID id);
}
