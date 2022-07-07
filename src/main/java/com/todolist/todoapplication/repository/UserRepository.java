package com.todolist.todoapplication.repository;

import com.todolist.todoapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User c set c.id = :newId WHERE c.id = :id")
    void updateUserId(@Param("id") String id, @Param("newId") String newId);
}
