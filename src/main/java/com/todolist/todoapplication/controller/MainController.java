package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "https://todo-application-frontend.herokuapp.com")
public class MainController {

    @Autowired
    private MainService todoService;

//    @GetMapping("/main")
//    public String main(@RequestParam(name = "name", required = false, defaultValue = "default") String name, Map<String, Object> model){
//        model.put("name", name);
//        return "main";
//    }

    @GetMapping("/main")
    public String showAll(@AuthenticationPrincipal User user, Model model) {
        List<Todo> todoList = todoService.fetchTodosByUser(user);
        model.addAttribute("todos", todoList);
        return "main";
    }

    @PostMapping("/todo")
    public String addTodo(@AuthenticationPrincipal User user,@RequestParam(value = "content", required = false) String content){
        todoService.createNewTodo(user, content);
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
