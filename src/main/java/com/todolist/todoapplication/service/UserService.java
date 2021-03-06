package com.todolist.todoapplication.service;

import com.todolist.todoapplication.dto.UserDTO;
import com.todolist.todoapplication.entity.PasswordResetToken;
import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.repository.RepositoryTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private RepositoryTodo repositoryTodo;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public UserDTO getUserById(String userId) {
        return userToUserDTO(repositoryTodo.getUserById(userId));
    }

    private UserDTO userToUserDTO(User user){
        return new UserDTO(user.getId(), user.getUsername());
    }

    public User findUserByEmail(String email) {
        return repositoryTodo.getUserByEmail(email);
    }

//    public void createPasswordResetTokenForUser(User user, String token) {
//        PasswordResetToken myToken = new PasswordResetToken(token, user);
//        passwordTokenRepository.save(myToken);
//    }
//
//    public void changeUserPassword(User user, String password) {
//        user.setPassword(passwordEncoder.encode(password));
//        repositoryTodo.saveUser(user);
//    }
//
//    public Optional getUserByPasswordResetToken(String token) {
//
//    }
}
