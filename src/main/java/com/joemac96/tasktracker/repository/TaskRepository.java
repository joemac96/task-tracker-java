package com.joemac96.tasktracker.repository;

import com.joemac96.tasktracker.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> { }