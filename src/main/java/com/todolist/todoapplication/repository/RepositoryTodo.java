package com.todolist.todoapplication.repository;

import com.todolist.todoapplication.entity.Todo;
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
        //Iterator<Todo> todoIterator = todoIterable.iterator();
        List<Todo> todoList = new ArrayList<>();
        todoIterable.forEach(todoList::add);
        return todoList;
    }

//    public List<User> fetchAllUsers() {
//        return userRepository.findAll();
//    }

    public void updateTodo(Long id, Todo todoItemContent) {
        todoItemsRepository.updateTodoInfoById(todoItemContent.getContent(), todoItemContent.getCompleted(), id);
    }

    public void createNewTodo(Todo todo) {
        todoItemsRepository.save(todo);
    }

    public void deleteTodo(Long id) {
//        Optional<Todo> optionalTodo = todoItemsRepository.findAll().
//                .stream()
//                .filter((item) -> item.getId().equals(id)).
//                findAny();
        Iterable<Todo> todoIterable = todoItemsRepository.findAll();

        for (Todo todo: todoIterable) {
            if (todo.getId().equals(id)){
                todoItemsRepository.delete(todo);
                break;
            }
        }

//        if (optionalTodo.isPresent()) {
//            Todo todo = optionalTodo.get();
//            todoItemsRepository.delete(todo);
//        } else {
//            throw new NoSuchElementException("todo not found!");
//        }


    }
}
