package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.request.AddTodoRequest;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private MainService todoService;

    @GetMapping("/api/todoItems")
    public ResponseEntity<?> fetchAllTodoItems() {
        List<Todo> todoList = todoService.fetchAllTodoItems();
        return ResponseEntity.ok(todoList);
    }

    @PostMapping("/api/todoItems")
    public ResponseEntity<?> createNewTodo(@RequestBody String content){
        Todo newTodo = todoService.createNewTodo(content);

        return ResponseEntity.ok(newTodo);
    }

//    @PostMapping("/{userId}/todos")
//    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
//        Todo todo = new Todo();
//        todo.setContent(todoRequest.getContent());
//        todoRepository.save(todo);
//        userRepository.save(user);
//    }

    @PutMapping("/api/todoItems/{id}") // +
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo updateTodo = todoService.updateTodo(id, todo);
        return ResponseEntity.ok(updateTodo);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted(@PathVariable Long todoId) {
        todoService.toggleTodoCompleted(todoId);
    }

    @DeleteMapping("/api/todoItems/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("ok");
    }


}
