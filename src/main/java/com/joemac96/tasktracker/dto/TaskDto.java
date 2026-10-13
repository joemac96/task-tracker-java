package com.joemac96.tasktracker.dto;

import java.time.Instant;

public record TaskDto(Long id,
                      String title,
                      String description,
                      Instant dueAt,
                      Instant createdAt,
                      Instant completedAt,
                      TaskStatus status) { }