package org.personal.tasktracker.repository;

import org.personal.tasktracker.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {
    private final Map<UUID, Task> tasks = new HashMap<>();

    public Task Get(UUID id) {
        return tasks.get(id);
    }

    public Task Create(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public Task Update(UUID id, Task task) {
        return tasks.put(id, task);
    }

    public Task Delete(UUID id) {
        return tasks.remove(id);
    }

    public List<Task> GetAll() {
        return new ArrayList<>(tasks.values());
    }
}