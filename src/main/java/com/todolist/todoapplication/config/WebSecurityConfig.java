package com.todolist.todoapplication.config;

import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.oauth2.CustomOAuth2UserSevice;
import com.todolist.todoapplication.repository.UserRepository;
import com.todolist.todoapplication.service.CustomOidcUserService;
import com.todolist.todoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomOAuth2UserSevice customOAuth2UserSevice;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       CustomOidcUserService oidcUserService = new CustomOidcUserService();

//        http.authorizeRequests()
//                    .antMatchers("/home", "/registration", "/login/**", "/oauth2/**" , "/img/**", "/css/**").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
////                    .successHandler(new RefererRedirectionAuthenticationSuccessHandler())
//                    .loginPage("/login")
//                    .permitAll()
//                .and().logout().permitAll()
//                .and()
//                .oauth2Login()
//                    .loginPage("/login")
//                .userInfoEndpoint().userService(customOAuth2UserSevice)
//                .and();

        http.authorizeRequests()
                .antMatchers("/home", "/registration", "/login/**", "/oauth2/**" , "/img/**", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll();

        http
                .oauth2Login()
                .loginPage("/login/oauth2");

    }

//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        List<ClientRegistration> registrations = clients.stream()
//                .map(c -> getRegistration(c))
//                .filter(registration -> registration != null)
//                .collect(Collectors.toList());
//
//        return new InMemoryClientRegistrationRepository(registrations);
//
//    }

//    @Bean
//    public OAuth2AuthorizedClientService authorizedClientService() {
//
//        return new InMemoryOAuth2AuthorizedClientService(
//                clientRegistrationRepository());
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
//        return map -> {
//            String id = (String) map.get("id");
//            User user = userRepository.findById(id).orElseGet(()->{
//                User newUser = new User();
//
//                newUser.setId(id);
//                newUser.setUsername((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                return newUser;
//            });
//
//
//            return userRepository.save(user);
//        };
//    }



}
