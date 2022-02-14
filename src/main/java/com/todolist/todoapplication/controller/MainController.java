package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public String main() {
        return "main";
    }

    @GetMapping("main/allTodos")
    public String showAll(Map<String, Object> model) {
        //model.addAttribute("todos", todoService.fetchAllTodoItems());
        model.put("todos", todoService.fetchAllTodoItems());
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
