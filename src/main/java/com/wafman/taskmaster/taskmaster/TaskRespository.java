package com.wafman.taskmaster.taskmaster;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface TaskRespository extends CrudRepository<Task, String> {
    Optional<Task> findById(String id);
}
