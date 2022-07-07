package com.todolist.todoapplication.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User user;

    public CustomOAuth2User() {
    }

    public CustomOAuth2User(OAuth2User user) {
        this.user = user;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }




    @Override
    public Map<String, Object> getAttributes() {
        return user.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getName() {
        return user.getAttribute("name");
    }

    public String getLogin() {
        return user.getAttribute("login");
    }

    public String getEmail() {
        return user.getAttribute("email");
    }
}
