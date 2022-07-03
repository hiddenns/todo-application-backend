package com.todolist.todoapplication.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddTodoRequest {

    private String content;


    private Boolean completed;
}
