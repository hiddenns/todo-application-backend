package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.request.AddTodoRequest;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService todoService;

    @GetMapping("/main")
    public String showAll(@AuthenticationPrincipal User user, Model model) {
        List<Todo> todoList = todoService.fetchTodosByUser(user);
        model.addAttribute("todos", todoList);
        model.addAttribute("username", user.getUsername());
        return "main";
    }

    @PostMapping("/todo")
    public String addTodo(@AuthenticationPrincipal User user,@RequestParam(value = "content", required = false) String content){
        todoService.createNewTodo(user, content);
        return "redirect:/main";
    }

//    @PostMapping("/add/todo")
//    public String updateTodo(@AuthenticationPrincipal User user, @RequestBody AddTodoRequest todo){
//        System.out.println("Todo Controller: " + todo.getContent());
//        return "redirect:/main";
//    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/main";
    }


}
