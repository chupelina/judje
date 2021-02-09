package com.softuni.model.entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity {
    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public String getName() {
        return name;
    }

    public ExerciseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public ExerciseEntity setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public ExerciseEntity setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
