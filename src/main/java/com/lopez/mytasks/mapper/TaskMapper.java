package com.lopez.mytasks.mapper;

import com.lopez.mytasks.domain.Task;
import com.lopez.mytasks.domain.TaskDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Task mapToTask(final TaskDto taskDto) {
        return new Task(taskDto.getId(), taskDto.getTitle(), taskDto.getContent());
    }

    public TaskDto mapToTaskDto(final Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getContent());
    }

    public List<TaskDto> mapToListTaskDto(final List<Task> taskList) {
        return taskList.stream()
                .map(task -> new TaskDto(task.getId(), task.getTitle(), task.getContent()))
                .collect(Collectors.toList());
    }
}
