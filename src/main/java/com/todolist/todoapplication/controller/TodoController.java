package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.request.AddTodoRequest;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class TodoController {

    @Autowired
    private MainService todoService;

    @GetMapping("/api/todoItems")
    public ResponseEntity<?> fetchAllTodoItems() {
        List<Todo> todoList = todoService.fetchAllTodoItems();
        return ResponseEntity.ok(todoList);
    }



//    @PutMapping("/todo")
//    public String updateTodo(@AuthenticationPrincipal User user,@RequestBody Todo todo){
//        System.out.println("Todo Controller: " + tod    @PostMapping("/api/addtodoItems")
//    public ResponseEntity<?> createNewTodo(@RequestBody String content){
//        Todo newTodo = todoService.createNewTodo(content);
//
//        return ResponseEntity.ok(newTodo);
//    }o.getContent());
//        return "redirect:/main";
//    }

//    @PutMapping("/api/todo/{id}") // +
//    public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody AddTodoRequest todo) {
////        Todo updateTodo = todoService.updateTodo(id, todo);
//        System.out.println("Todo Controller update: " + todo.getContent());
//        return ResponseEntity.ok(todo);
//    }

//    @PostMapping("/api/todo") // +
//    public String updateTodo(@RequestBody AddTodoRequest todo) {
////        Todo updateTodo = todoService.updateTodo(id, todo);
//        System.out.println("Todo Controller: " + todo.getContent());
//        return "redirect:/main";
//    }

    @PostMapping("/api/todo/toggle/{id}")
    public String toggleTodoCompleted(@PathVariable Long id) {
        System.out.println("toggle");
        todoService.toggleTodoCompleted(id);
        return "redirect:/main";
    }

    @PostMapping("/api/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/main";
    }

    @PostMapping("/api/todo/{id}") // +
    public String updateTodo(@AuthenticationPrincipal User user, @PathVariable Long id,
                             @ModelAttribute AddTodoRequest todo) {
//        @RequestParam(value = "content", required = false) String content,
//        @RequestParam(value = "completed", required = false) boolean completed
        Todo tempTodo = new Todo(id, todo.getContent(), todo.getCompleted(), user);
        Todo updateTodo = todoService.updateTodo(id, tempTodo);
        if (todo.getCompleted()) {
            System.out.println("Todo Controller update: tre=ue" + todo.getContent());
        }
        return "redirect:/main";
    }



}
