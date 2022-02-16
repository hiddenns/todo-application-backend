package com.todolist.todoapplication.repository;

import com.todolist.todoapplication.entity.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    @Transactional
    @Modifying
    @Query("update Todo t set t.content = ?1, t.completed = ?2 where t.id = ?3")
    void updateTodoInfoById(String content, Boolean completed, Long id);

    List<Todo> findTodosByUserId(Long id);
    Todo findTodoById(Long id);

}
