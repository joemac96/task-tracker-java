package com.joemac96.tasktracker.service;

import com.joemac96.tasktracker.dto.CreateTaskRequest;
import com.joemac96.tasktracker.dto.TaskDto;
import com.joemac96.tasktracker.dto.UpdateTaskRequest;
import com.joemac96.tasktracker.entity.Task;
import com.joemac96.tasktracker.mapper.TaskMapper;
import com.joemac96.tasktracker.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
        List<TaskDto> results = new ArrayList<>();
        taskRepository.findAll()
                      .forEach(t -> results.add(taskMapper.toDto(t)));
        return results;
    }

    public TaskDto getById(long id) {
        Task existing = taskRepository.findById(id)
                                      .orElseThrow(EntityNotFoundException::new);
        return taskMapper.toDto(existing);
    }

    public TaskDto create(CreateTaskRequest request) {
        Task created = taskRepository.save(taskMapper.toEntity(request));
        return taskMapper.toDto(created);
    }

    public TaskDto update(long id, UpdateTaskRequest request) {
        Task existing = taskRepository.findById(id)
                                      .orElseThrow(EntityNotFoundException::new);

        existing.setTitle(request.title());
        existing.setDescription(request.description());
        existing.setDueAt(request.dueAt());

        Task updated = taskRepository.save(existing);
        return taskMapper.toDto(updated);
    }

    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto complete(long id) {
        Task existing = taskRepository.findById(id)
                                      .orElseThrow(EntityNotFoundException::new);

        if (existing.getCompletedAt() != null) {
            return taskMapper.toDto(existing);
        }

        existing.setCompletedAt(Instant.now(clock));

        Task updated = taskRepository.save(existing);
        return taskMapper.toDto(updated);
    }
}