package com.todolist.todoapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
//        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/resourses/**")
//                .addResourceLocations("/src/main/resources/static/", "classpath:/src/main/resources/static/");
//    }
}
