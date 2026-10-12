package org.personal.tasktracker.entity;

import java.time.Instant;

public class Task extends BaseEntity {
    private String Title;
    private String Description;
    private Instant DueAt;
    private Instant CompletedAt;

    public Task(String title, String description, Instant dueAt, String createdBy) {
        super(createdBy);
        this.Title = title;
        this.Description = description;
        this.DueAt = dueAt;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public Instant getDueAt() {
        return this.DueAt;
    }

    public void setDueAt(Instant dueAt) {
        this.DueAt = dueAt;
    }

    public Instant getCompletedAt() {
        return this.CompletedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.CompletedAt = completedAt;
    }
}