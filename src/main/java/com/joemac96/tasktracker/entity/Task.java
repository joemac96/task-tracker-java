package com.joemac96.tasktracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    @Column(nullable = false)
    private String Title;
    private String Description;
    private Instant DueAt;
    private Instant CompletedAt;

    protected Task() { }

    public Task(String title, String description, Instant dueAt) {
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