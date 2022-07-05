package com.todolist.todoapplication;

import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.repository.TodoRepository;
import com.todolist.todoapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
//
//	@Bean
//	public CommandLineRunner dataLoader(UserRepository userRepository) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//				userRepository.save(new User("1","1"));
//			}
//		};
//	}

}
