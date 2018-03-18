package com.lopez.mytasks.repository;

import com.lopez.mytasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();
    Optional<Task> findById(Long id);
    @Override
    Task save(Task task);
    @Override
    void delete(Long id);
    @Override
    Task findOne(Long id);
}
