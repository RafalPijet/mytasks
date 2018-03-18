package com.lopez.mytasks.controller;

import com.lopez.mytasks.domain.ChangeProcessDto;
import com.lopez.mytasks.domain.InfoProcessDto;
import com.lopez.mytasks.domain.Task;
import com.lopez.mytasks.domain.TaskDto;
import com.lopez.mytasks.mapper.TaskMapper;
import com.lopez.mytasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/getTasks")
    public List<TaskDto> getTasks() {
        return mapper.mapToListTaskDto(dbService.getTasks());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return mapper.mapToTaskDto(dbService.getTaskWithId(taskId).orElseThrow(TaskNotFoundException::new));
    }
    @RequestMapping(method = RequestMethod.POST, value = "/createTask", consumes = APPLICATION_JSON_VALUE)
    public InfoProcessDto createTask(@RequestBody TaskDto taskDto) {
        TaskDto result = mapper.mapToTaskDto(dbService.saveTask(mapper.mapToTask(taskDto)));
        return new InfoProcessDto("The record has been added", result.getId(), result.getTitle(), result.getContent());
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/updateTask", consumes = APPLICATION_JSON_VALUE)
    public ChangeProcessDto updateTask(@RequestBody TaskDto taskDto) {
        Task oldTask = new Task(dbService.findTask(taskDto.getId()).getId(), dbService.findTask(taskDto.getId()).getTitle(), dbService.findTask(taskDto.getId()).getContent());
        Task newTask = dbService.saveTask(mapper.mapToTask(taskDto));
        return new ChangeProcessDto(newTask.getId(), "The record has been updated from ...", oldTask.getTitle(), oldTask.getContent(),
                                "The record has been updated to ...", newTask.getTitle(), newTask.getContent());
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteTask")
    public InfoProcessDto deleteTask(@RequestParam Long taskId) throws TaskNotFoundException{
        Task result = dbService.deleteTask(taskId);
        return new InfoProcessDto("The record has been deleted", result.getId(), result.getTitle(), result.getContent());
    }
}
