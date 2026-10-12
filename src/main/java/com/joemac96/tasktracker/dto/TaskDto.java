package com.joemac96.tasktracker.dto;

import java.time.Instant;
import java.util.UUID;

public record TaskDto(UUID id,
                      String title,
                      String description,
                      Instant dueAt,
                      Instant createdAt,
                      Instant completedAt,
                      TaskStatus status) { }