package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.model.AuthenticationProvider;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "redirect:/login";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, Map<String, Object> model, BindingResult result) {

        if (mainService.findUserByUsername(user.getUsername()) != null ) {
            model.put("usernameExists", "Username is exists!");
            return "login";
        } else if (mainService.findUserByEmail(user.getEmail()) != null) {
            model.put("emailExists", "Email is exists!");
            return "login";
        }

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setAuthProvider(AuthenticationProvider.LOCAL);
        mainService.saveUser(user);
        model.put("registrationSuccessful", "Registration successful!");
        return "login";
    }


}
