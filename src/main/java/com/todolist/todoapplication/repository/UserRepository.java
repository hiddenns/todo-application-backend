package com.todolist.todoapplication.repository;

import com.todolist.todoapplication.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
