package com.joemac96.tasktracker.controller;

import com.joemac96.tasktracker.dto.CreateTaskRequest;
import com.joemac96.tasktracker.dto.TaskDto;
import com.joemac96.tasktracker.dto.UpdateTaskRequest;
import com.joemac96.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    public TaskDto GetTaskById(@PathVariable UUID id) {
        return taskService.getById(id);
    }

    @GetMapping
    public List<TaskDto> GetTasks() {
        return taskService.getAll();
    }

    @PostMapping
    public TaskDto createTask(@RequestBody @Valid CreateTaskRequest request) {
        return taskService.create(request);
    }

    @PutMapping("{id}")
    public TaskDto updateTask(@PathVariable UUID id,
                              @RequestBody @Valid UpdateTaskRequest request) {
        return taskService.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteTask(@PathVariable UUID id) {
        taskService.delete(id);
    }

    @PutMapping("{id}/complete")
    public TaskDto updateTask(@PathVariable UUID id) {
        return taskService.complete(id);
    }
}