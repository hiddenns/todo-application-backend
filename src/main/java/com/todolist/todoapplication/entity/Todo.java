package com.todolist.todoapplication.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name="todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Boolean completed = Boolean.FALSE;

    public Todo(){
        this.content = "TASK";
    }

    public Todo(Long id, String content, Boolean completed) {
        this.id = id;
        this.content = content;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
