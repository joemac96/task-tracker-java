package org.personal.tasktracker.service;

import org.personal.tasktracker.dto.CreateTaskRequest;
import org.personal.tasktracker.dto.TaskDto;
import org.personal.tasktracker.entity.Task;
import org.personal.tasktracker.mapper.TaskMapper;
import org.personal.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskDto> getAll() {
        return taskRepository.GetAll()
                             .stream()
                             .map(taskMapper::toDto)
                             .toList();
    }

    public TaskDto getById(UUID id) {
        Task task = taskRepository.Get(id);
        return taskMapper.toDto(task);
    }

    public TaskDto create(CreateTaskRequest request) {
        Task created = taskRepository.Create(taskMapper.toEntity(request));
        return taskMapper.toDto(created);
    }
}