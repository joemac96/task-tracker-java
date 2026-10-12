package personal.tasktracker.repository;

import org.springframework.stereotype.Repository;
import personal.tasktracker.entity.Task;

import java.util.*;

@Repository
public class TaskRepository {
    private final Map<UUID, Task> tasks = new HashMap<>();

    public Task get(UUID id) {
        return tasks.get(id);
    }

    public Task create(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public Task update(UUID id, Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public Task delete(UUID id) {
        return tasks.remove(id);
    }

    public List<Task> getAll() {
        return new ArrayList<>(tasks.values());
    }
}