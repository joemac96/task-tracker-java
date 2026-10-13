package com.joemac96.tasktracker;

import com.joemac96.tasktracker.dto.CreateTaskRequest;
import com.joemac96.tasktracker.dto.TaskDto;
import com.joemac96.tasktracker.dto.TaskStatus;
import com.joemac96.tasktracker.entity.Task;
import com.joemac96.tasktracker.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskMapperTests {
    private final Instant now = Instant.parse("2026-10-13T12:00:00Z");
    private final Clock clock = Clock.fixed(now, ZoneOffset.UTC);
    private final TaskMapper taskMapper = new TaskMapper(clock);

    @Test
    public void toEntity_Returns_Entity() {
        CreateTaskRequest request = new CreateTaskRequest("Task 1",
                                                          "This is a task",
                                                          Instant.parse("2026-10-13T12:00:00Z"));
        Task result = taskMapper.toEntity(request);
        assertThat(request.title()).isEqualTo(result.getTitle());
    }

    @Test
    public void toDto_Returns_Dto() {
        Task task = new Task("Task 1", "This is a task", Instant.parse("2026-10-20T12:00:00Z"));
        ReflectionTestUtils.setField(task, "id", 1L);

        TaskDto result = taskMapper.toDto(task);

        assertThat(result.id()).isEqualTo(task.getId());
    }

    @Test
    public void completed_When_CompletedAtHasValue() {
        Task task = new Task("Task 1", "This is a task", Instant.parse("2026-10-13T12:00:00Z"));
        task.setCompletedAt(Instant.now());

        TaskDto result = taskMapper.toDto(task);

        assertThat(result.status()).isEqualTo(TaskStatus.COMPLETED);
    }

    @Test
    public void expired_When_DueAtHasPassed_And_CompleteAtIsNull() {
        Instant dueAt = Instant.parse("2026-10-12T12:00:00Z");
        Task task = new Task("Task 1", "This is a task", dueAt);

        TaskDto result = taskMapper.toDto(task);

        assertThat(result.status()).isEqualTo(TaskStatus.EXPIRED);
        assertThat(result.completedAt()).isNull();
    }

    @Test
    public void pending_When_DueAtIsInFuture_And_CompletedAtIsNull() {
        Instant dueAt = Instant.parse("2026-10-20T12:00:00Z");
        Task task = new Task("Task 1", "This is a task", dueAt);

        TaskDto result = taskMapper.toDto(task);

        assertThat(result.status()).isEqualTo(TaskStatus.PENDING);
        assertThat(result.completedAt()).isNull();
        assertThat(dueAt).isAfter(now);
    }

    @Test
    public void pending_When_DueAtEqualsNow_And_CompletedAtIsNull() {
        Instant dueAt = now;
        Task task = new Task("Task 1", "This is a task", dueAt);

        TaskDto result = taskMapper.toDto(task);

        assertThat(result.status()).isEqualTo(TaskStatus.PENDING);
        assertThat(result.completedAt()).isNull();
        assertThat(dueAt).isEqualTo(now);
    }
}