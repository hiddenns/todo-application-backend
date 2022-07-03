package com.todolist.todoapplication.service;

import com.todolist.todoapplication.dto.UserDTO;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.repository.RepositoryTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private RepositoryTodo repositoryTodo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositoryTodo.findUserNyUsername(username);
    }

    public List<UserDTO> fetchAllUsers() {
        List<User> userList = repositoryTodo.fetchAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            userDTOList.add(userToUserDTO(user));
        }

        userList.stream()
                .map( user -> userDTOList.add(userToUserDTO(user)));

        return userDTOList;
    }

    public UserDTO createUser(User user) {
        repositoryTodo.saveUser(user);
        return userToUserDTO(user);
    }

    public UserDTO getUserById(Long userId) {
        return userToUserDTO(repositoryTodo.getUserById(userId));
    }

    private UserDTO userToUserDTO(User user){
        return new UserDTO(user.getId(), user.getUsername());
    }

}
