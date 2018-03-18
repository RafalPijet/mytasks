package com.lopez.mytasks.service;

import com.lopez.mytasks.controller.TaskNotFoundException;
import com.lopez.mytasks.domain.Task;
import com.lopez.mytasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getTasks() {
        return repository.findAll();
    }
    public Optional<Task> getTaskWithId(final Long id) {
        return repository.findById(id);
    }
    public Task saveTask(final Task task) {
        return repository.save(task);
    }
    public Task deleteTask(final Long id) throws TaskNotFoundException {
        if (repository.exists(id)) {
            Task result = repository.findOne(id);
            repository.delete(id);
            return result;
        } else {
            throw new TaskNotFoundException();
        }
    }
    public Task findTask(Long id) {
        return repository.findOne(id);
    }

}
