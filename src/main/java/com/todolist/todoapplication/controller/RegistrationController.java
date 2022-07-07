package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private MainService mainService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = mainService.findUserByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message", "Username is exists!");
            return "redirect:/login";
        }

        mainService.saveUser(user);
        System.out.println("controller: user save");
        return "redirect:/login";
    }


}
