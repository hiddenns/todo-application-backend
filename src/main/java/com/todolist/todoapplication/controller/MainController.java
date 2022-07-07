package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.model.AuthenticationProvider;
import com.todolist.todoapplication.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService todoService;

    @GetMapping("/main")
    public String showAll(@AuthenticationPrincipal OAuth2User oAuth2User, @AuthenticationPrincipal User localAuthUser, Model model)
            throws AuthException {

        User user = defineUser(localAuthUser, oAuth2User);

        List<Todo> todoList = todoService.fetchTodosByUser(user);
        model.addAttribute("todos", todoList);
        model.addAttribute("username", user.getUsername());
        return "main";
    }

    @PostMapping("/todo")
    public String addTodo(@AuthenticationPrincipal User localUser,
                          @AuthenticationPrincipal OAuth2User oAuth2User,
                          @RequestParam(value = "content", required = false) String content){
        User user = null;
        try {
            user = defineUser(localUser, oAuth2User);
        } catch (AuthException e) {
            e.printStackTrace();
        }
        todoService.createNewTodo(user, content);
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/main";
    }

    private User defineUser(User localAuthUser, OAuth2User oAuth2User) throws AuthException {
        User user = new User();
        if (localAuthUser != null) {
            user = localAuthUser;
            user.setAuthProvider(AuthenticationProvider.LOCAL);
        } else if (oAuth2User != null) {
            user.setId(oAuth2User.getAttribute("sub"));
            user.setUsername(oAuth2User.getAttribute("name"));
            user.setEmail(oAuth2User.getAttribute("email"));
            user.setAuthProvider(AuthenticationProvider.GOOGLE);
        } else {
            throw new AuthException();
        }
        return user;
    }

//    @PostMapping("/add/todo")
//    public String updateTodo(@AuthenticationPrincipal User user, @RequestBody AddTodoRequest todo){
//        System.out.println("Todo Controller: " + todo.getContent());
//        return "redirect:/main";
//    }
}
