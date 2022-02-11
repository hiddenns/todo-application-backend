package com.todolist.todoapplication.repository;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class RepositoryTodo {
    private UserRepository userRepository;
    private TodoRepository todoItemsRepository;

    public RepositoryTodo(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoItemsRepository = todoRepository;
    }

    public List<Todo> fetchAllTodoItems(){
        return todoItemsRepository.findAll();
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public void updateTodo(Long id, Todo todoItemContent) {
        todoItemsRepository.updateTodoInfoById(todoItemContent.getContent(), todoItemContent.getCompleted(), id);
    }

    public void createNewTodo(Todo todo) {
        todoItemsRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        Optional<Todo> optionalTodo = todoItemsRepository.findAll()
                .stream()
                .filter((item) -> item.getId().equals(id)).
                findAny();

        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todoItemsRepository.delete(todo);
        } else {
            throw new NoSuchElementException("todo not found!");
        }


    }
}
