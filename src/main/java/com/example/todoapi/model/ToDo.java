package com.example.todoapi.model;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ToDo {
    private String id;

    @NotNull(message = "required")
    @NotBlank(message = "required")
    @Size(max = 120, message = "Less than 120 characters")
    private String name;

    private LocalDate dueDate;

    private Boolean isDone;

    private LocalDate doneAt;

    @NotNull
    @Min(0)
    @Max(3)
    private Integer priority;

    private LocalDate createdAd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public LocalDate getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDate doneAt) {
        this.doneAt = doneAt;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDate getCreatedAd() {
        return createdAd;
    }

    public void setCreatedAd(LocalDate createdAd) {
        this.createdAd = createdAd;
    }
}
