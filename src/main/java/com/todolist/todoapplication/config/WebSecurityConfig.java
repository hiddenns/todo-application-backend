package com.todolist.todoapplication.config;

import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.repository.UserRepository;
import com.todolist.todoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.time.LocalDateTime;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/home", "/registration", "/api/todoItems" , "/img/**", "/css/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
//                    .successHandler(new RefererRedirectionAuthenticationSuccessHandler())
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = (String) map.get("sub");
            User newUser = userRepository.findById(id).orElseGet(()->{
                User user = new User();

                user.setId((String) map.get("sub"));
                user.setUsername((String) map.get("name"));
                user.setEmail((String) map.get("email"));
                return user;
            });
            //newUser.setLastVisit(LocalDateTime.now());

            userRepository.save(newUser);
            return newUser;
        };
    }



}
