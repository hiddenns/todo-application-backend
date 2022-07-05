package com.todolist.todoapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="usr")
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "prod"),
            strategy = "com.todolist.todoapplication.model.ImeiIdGenerator")
    private String id;

    private String username;
    private String password;
    private String email;

//    private LocalDateTime lastVisit;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @PrePersist
    private void ensureId(){
        this.setId(UUID.randomUUID().toString());
    }
}
