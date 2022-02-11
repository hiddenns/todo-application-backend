package com.todolist.todoapplication;

import com.todolist.todoapplication.entity.Todo;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.repository.TodoRepository;
import com.todolist.todoapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TodoApplication { //implements CommandLineRunner

	//@Autowired
	//private UserRepository userRepository;

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		//User user = new User();
//		//user.setUsername("Andrii");
//		//user.setPassword("1234");
//
//		//User user2 = new User(2L, "Bogdan", "1234567", null);
//
//		Todo todo = new Todo();
//		todo.setContent("Learn Spring!");
//
//		Todo todo2 = new Todo();
//		todo2.setContent("Learn Hibernate!");
//		todo2.setCompleted(Boolean.TRUE);
//
//		//user.getTodoList().add(todo);
//		//user.getTodoList().add(todo2);
//
//		todoRepository.save(todo);
//		todoRepository.save(todo2);
//
//		//userRepository.save(user);
//		//userRepository.save(user2);
//
//	}
}
