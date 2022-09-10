package com.example.todoapi.model;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

public class ToDo{
    private String id;

    @NotNull(message = "required")
    @NotBlank(message = "required")
    @Size(max = 120, message = "Less than 120 characters")
    private String name;

    private LocalDate dueDate;

    private Boolean isDone;

    private LocalDateTime doneAt;

    @NotNull
    @Min(1)
    @Max(3)
    private Integer priority;

    private LocalDateTime createdAt;

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

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static Comparator<ToDo> DueDateComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo o1, ToDo o2) {
            if(o1.getDueDate() == null || o2.getDueDate() == null){
                return 0;
            }
            return o1.getDueDate().compareTo(o2.getDueDate());
        }
    };

    public static Comparator<ToDo> PriorityComparator = new Comparator<ToDo>() {
        @Override
        public int compare(ToDo o1, ToDo o2) {
            return o1.getPriority() - o2.getPriority();
        }
    };
}
