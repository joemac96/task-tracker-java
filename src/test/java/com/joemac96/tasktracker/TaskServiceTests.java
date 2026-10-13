package com.joemac96.tasktracker;

import com.joemac96.tasktracker.dto.TaskDto;
import com.joemac96.tasktracker.entity.Task;
import com.joemac96.tasktracker.mapper.TaskMapper;
import com.joemac96.tasktracker.repository.TaskRepository;
import com.joemac96.tasktracker.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskServiceTests {
    private final TaskRepository taskRepository = mock(TaskRepository.class);
    private final Clock clock = Clock.fixed(Instant.parse("2026-10-13T12:00:00Z"), ZoneOffset.UTC);
    private final TaskMapper taskMapper = new TaskMapper(clock);
    private final TaskService taskService = new TaskService(taskRepository, taskMapper, clock);

    @Test
    public void getThrowsWhenTaskNotFound() {
        long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.getById(taskId)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void getReturnsTask() {
        Task task = new Task("Task 1", "This is a task", Instant.parse("2026-10-20T12:00:00Z"));
        ReflectionTestUtils.setField(task, "id", 1L);

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        TaskDto result = taskService.getById(task.getId());

        assertThat(result.id()).isEqualTo(task.getId());
    }
}