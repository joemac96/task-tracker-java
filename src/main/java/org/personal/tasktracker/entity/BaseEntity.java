package org.personal.tasktracker.entity;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseEntity {
    private final UUID Id;
    private final Instant CreatedAt;
    private final String CreatedBy;
    private Instant UpdatedAt;
    private String UpdatedBy;

    public BaseEntity(String createdBy) {
        this.Id = UUID.ofEpochMillis(System.currentTimeMillis());
        this.CreatedAt = Instant.now();
        this.CreatedBy = createdBy;
    }

    public UUID getId() {
        return this.Id;
    }

    public Instant getCreatedAt() {
        return this.CreatedAt;
    }

    public Instant getUpdatedAt() {
        return this.UpdatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.UpdatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.CreatedBy;
    }

    public String getUpdatedBy() {
        return this.UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.UpdatedBy = updatedBy;
    }
}