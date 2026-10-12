package personal.tasktracker.mapper;

import personal.tasktracker.dto.CreateTaskRequest;
import personal.tasktracker.dto.TaskDto;
import personal.tasktracker.dto.TaskStatus;
import personal.tasktracker.entity.Task;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;

@Component
public class TaskMapper {
    private final Clock clock;

    public TaskMapper(Clock clock) {
        this.clock = clock;
    }

    public TaskDto toDto(Task task) {
        return new TaskDto(task.getId(),
                           task.getTitle(),
                           task.getDescription(),
                           task.getDueAt(),
                           task.getCreatedAt(),
                           task.getCompletedAt(),
                           task.getCompletedAt() != null ?
                                   TaskStatus.COMPLETED :
                                   task.getDueAt()
                                       .isBefore(Instant.now(clock)) ?
                                           TaskStatus.EXPIRED :
                                           TaskStatus.PENDING);
    }

    public Task toEntity(CreateTaskRequest request) {
        return new Task(request.title(), request.description(), request.dueAt(), "");
    }
}