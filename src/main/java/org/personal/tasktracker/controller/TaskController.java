package org.personal.tasktracker.controller;

import jakarta.validation.Valid;
import org.personal.tasktracker.dto.CreateTaskRequest;
import org.personal.tasktracker.dto.TaskDto;
import org.personal.tasktracker.service.TaskService;
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
}