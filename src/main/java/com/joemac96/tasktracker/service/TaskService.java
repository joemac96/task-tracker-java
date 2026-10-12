package com.joemac96.tasktracker.service;

import com.joemac96.tasktracker.common.exception.NotFoundException;
import com.joemac96.tasktracker.dto.CreateTaskRequest;
import com.joemac96.tasktracker.dto.TaskDto;
import com.joemac96.tasktracker.dto.UpdateTaskRequest;
import com.joemac96.tasktracker.entity.Task;
import com.joemac96.tasktracker.mapper.TaskMapper;
import com.joemac96.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final Clock clock;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, Clock clock) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.clock = clock;
    }

    public List<TaskDto> getAll() {
        return taskRepository.getAll()
                             .stream()
                             .map(taskMapper::toDto)
                             .toList();
    }

    public TaskDto getById(UUID id) {
        Task existing = taskRepository.get(id);
        if (existing == null) {
            throw new NotFoundException(Task.class, id);
        }

        return taskMapper.toDto(existing);
    }

    public TaskDto create(CreateTaskRequest request) {
        Task created = taskRepository.create(taskMapper.toEntity(request));
        return taskMapper.toDto(created);
    }

    public TaskDto update(UUID id, UpdateTaskRequest request) {
        Task existing = taskRepository.get(id);
        if (existing == null) {
            throw new NotFoundException(Task.class, id);
        }

        existing.setTitle(request.title());
        existing.setDescription(request.description());
        existing.setDueAt(request.dueAt());

        Task updated = taskRepository.update(id, existing);
        return taskMapper.toDto(updated);
    }

    public void delete(UUID id) {
        taskRepository.delete(id);
    }

    public TaskDto complete(UUID id) {
        Task existing = taskRepository.get(id);
        if (existing == null) {
            throw new NotFoundException(Task.class, id);
        }

        if (existing.getCompletedAt() != null) {
            return taskMapper.toDto(existing);
        }

        existing.setCompletedAt(Instant.now(clock));

        Task updated = taskRepository.update(id, existing);
        return taskMapper.toDto(updated);
    }
}