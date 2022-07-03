package com.todolist.todoapplication.controller;

import com.todolist.todoapplication.dto.UserDTO;
import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.repository.TodoRepository;
import com.todolist.todoapplication.repository.UserRepository;
import com.todolist.todoapplication.request.AddTodoRequest;
import com.todolist.todoapplication.request.AddUserRequest;
import com.todolist.todoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "https://todo-application-frontend.herokuapp.com")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/")
    public UserDTO addUser(@RequestBody AddUserRequest userRequest){
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        UserDTO userDTO = userService.createUser(user);

        return userDTO;
    }

//    @PostMapping("/{userId}/todos")
//    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
//        Todo todo = new Todo();
//        todo.setContent(todoRequest.getContent());
//        todoRepository.save(todo);
//        userRepository.save(user);
//    }


//    @DeleteMapping("/{userId}/todos/{todoId}")
//    public void deleteTodo(@PathVariable Long userId ,@PathVariable Long todoId) {
//        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
//        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
//        todoRepository.delete(todo);
//    }

//    @DeleteMapping("/{userId}")
//    public void deleteUser(@PathVariable Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
//        userRepository.delete(user);
//    }


}
