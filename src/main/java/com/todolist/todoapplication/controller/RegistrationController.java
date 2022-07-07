package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.model.AuthenticationProvider;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private MainService mainService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, Map<String, Object> model, BindingResult result) {
        User userFromDb = mainService.findUserByUsername(user.getUsername());


        if (userFromDb != null) {
            model.put("usernameExists", "Username is exists!");
            System.out.println("Username is exists!");

            return "login";
        }

        user.setAuthProvider(AuthenticationProvider.LOCAL);
        mainService.saveUser(user);
        return "redirect:/login";
    }


}
