package com.todolist.todoapplication.repository;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RepositoryTodo {
    private UserRepository userRepository;
    private TodoRepository todoItemsRepository;

    public RepositoryTodo(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoItemsRepository = todoRepository;
    }

    public List<Todo> fetchAllTodoItems(){
        Iterable<Todo> todoIterable = todoItemsRepository.findAll();
        List<Todo> todoList = new ArrayList<>();
        todoIterable.forEach(todoList::add);
        return todoList;
    }

    public List<User> fetchAllUsers() { ////// ???
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("Nikita");
        user1.setUsername("123456");

        User user2 = new User();
        user2.setUsername("Nikita 2");
        user2.setUsername("123456");

        userList.add(user1);
        userList.add(user2);

        return userList;
    }

    public Todo getTodoById(Long id){
        return todoItemsRepository.findTodoById(id);
    }

    public void updateTodo(Long id, Todo todoItemContent) {
        todoItemsRepository.updateTodoInfoById(todoItemContent.getContent(), todoItemContent.getCompleted(), id);
    }

    public void createNewTodo(Todo todo) {
        todoItemsRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        Iterable<Todo> todoIterable = todoItemsRepository.findAll();

        for (Todo todo: todoIterable) {
            if (todo.getId().equals(id)){
                todoItemsRepository.delete(todo);
                break;
            }
        }

    }

    public User findUserNyUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<Todo> findTodosByUser(User user) {
        List<Todo> list = todoItemsRepository.findTodosByUserId(user.getId());
        return list;
    }

    public void saveTodo(Todo todo) {
        todoItemsRepository.save(todo);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }
}
