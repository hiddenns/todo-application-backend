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
        //Iterator<Todo> todoIterator = todoIterable.iterator();
        List<Todo> todoList = new ArrayList<>();
        todoIterable.forEach(todoList::add);
        return todoList;
    }

    public List<User> fetchAllUsers() {
        //return userRepository.findAll();
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

    public User findUserNyUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        System.out.println("rep: user save");
        userRepository.save(user);
    }

    public List<Todo> findTodosByUser(User user) {
        List<Todo> list = todoItemsRepository.findTodosByUserId(user.getId());
        System.out.println("rep: user id = " + user.getId());
        list.stream().forEach(todo -> {
            System.out.println("rep: " + todo.getContent());
        });
       return list;
    }
}
