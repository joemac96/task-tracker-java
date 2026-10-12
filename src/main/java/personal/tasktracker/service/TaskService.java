package personal.tasktracker.service;

import org.springframework.stereotype.Service;
import personal.tasktracker.dto.CreateTaskRequest;
import personal.tasktracker.dto.TaskDto;
import personal.tasktracker.dto.UpdateTaskRequest;
import personal.tasktracker.entity.Task;
import personal.tasktracker.mapper.TaskMapper;
import personal.tasktracker.repository.TaskRepository;

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
        return taskRepository.getAll()
                             .stream()
                             .map(taskMapper::toDto)
                             .toList();
    }

    public TaskDto getById(UUID id) {
        Task task = taskRepository.get(id);
        return taskMapper.toDto(task);
    }

    public TaskDto create(CreateTaskRequest request) {
        Task created = taskRepository.create(taskMapper.toEntity(request));
        return taskMapper.toDto(created);
    }

    public TaskDto update(UUID id, UpdateTaskRequest request) {
        Task existing = taskRepository.get(id);

        existing.setTitle(request.title());
        existing.setDescription(request.description());
        existing.setDueAt(request.dueAt());

        Task updated = taskRepository.update(id, existing);
        return taskMapper.toDto(updated);
    }

    public void delete(UUID id) {
        taskRepository.delete(id);
    }
}