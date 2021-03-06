package com.todolist.todoapplication.service;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.repository.RepositoryTodo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class MainService {

    @Autowired
    RepositoryTodo repositoryTodo;

    public List<Todo> fetchAllTodoItems(){
        return repositoryTodo.fetchAllTodoItems();
    }

    public Todo updateTodo(String id, Todo todo) {
        repositoryTodo.updateTodo(id, todo);
        return todo;
    }

//    public Todo createNewTodo(String content) { // delete
//        Todo todo = new Todo();
//        todo.setContent(content);
//        repositoryTodo.createNewTodo(todo);
//        return todo;
//    }

    public Todo createNewTodo(User user, String content) {
        Todo todo = new Todo();
        todo.setContent(content);
        todo.setUser(user);
        repositoryTodo.createNewTodo(todo);
        return todo;
    }

    public void deleteTodo(String id) {
        repositoryTodo.deleteTodo(id);
    }

    public User findUserByUsername(String username) {
        return repositoryTodo.findUserNyUsername(username);
    }

    public void saveUser(User user) {
        repositoryTodo.saveUser(user);
    }

    public List<Todo> fetchTodosByUser(User user) {
        return repositoryTodo.findTodosByUser(user);
    }

    public void toggleTodoCompleted(String todoId) {
        Todo todo = repositoryTodo.getTodoById(todoId);
        //Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        todo.setCompleted(!todo.getCompleted());
        repositoryTodo.saveTodo(todo);
    }

    public User findUserByEmail(String email) {
        return repositoryTodo.findUserByEmail(email);
    }
}
