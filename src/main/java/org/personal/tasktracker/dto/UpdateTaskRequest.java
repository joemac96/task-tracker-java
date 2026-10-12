package org.personal.tasktracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record UpdateTaskRequest(@NotBlank String title,
                                @NotBlank String description,
                                @NotNull Instant dueAt) { }