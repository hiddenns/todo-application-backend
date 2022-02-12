package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/api/test")
    public ResponseEntity<?> testFunc(){
        return ResponseEntity.ok("it`s work");
    }

    @GetMapping("/api/todoItems")
    public ResponseEntity<?> fetchAllTodoItems(){
        List<Todo> todoList = todoService.fetchAllTodoItems();
        return ResponseEntity.ok(todoList);
    }

//    @GetMapping("/api/users")
//    public ResponseEntity<?> fetchAllUsers(){
//        List<User> userList = todoService.fetchAllUsers();
//        return ResponseEntity.ok(userList);
//    }

    @PostMapping("/api/todoItems")
    public ResponseEntity<?> createNewTodo(@RequestBody String content){
        Todo newTodo = todoService.createNewTodo(content);

        return ResponseEntity.ok(newTodo);
    }

    @PutMapping("/api/todoItems/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        Todo updateTodo = todoService.updateTodo (id, todo);
        return ResponseEntity.ok(updateTodo);
    }

    @DeleteMapping("/api/todoItems/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("ok");
    }

}
