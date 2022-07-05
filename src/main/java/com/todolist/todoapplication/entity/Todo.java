package com.todolist.todoapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="todo")
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "prod"),
            strategy = "com.todolist.todoapplication.model.ImeiIdGenerator")
    private String id;

    private String content;
    private Boolean completed = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(){
        this.content = "TASK";
    }

}
