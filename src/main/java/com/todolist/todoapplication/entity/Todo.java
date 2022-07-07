package com.todolist.todoapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name="todo")
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "todo"),
            strategy = "com.todolist.todoapplication.model.ImeiIdGenerator")
    private String id;

    private String content;
    private Boolean completed = Boolean.FALSE;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(){
        this.content = "TASK";
    }

}
