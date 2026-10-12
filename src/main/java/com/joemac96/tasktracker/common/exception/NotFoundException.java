package com.joemac96.tasktracker.common.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> entity, Object id) {
        super("%s with id '%s' does not exist".formatted(entity.getSimpleName(), id));
    }
}