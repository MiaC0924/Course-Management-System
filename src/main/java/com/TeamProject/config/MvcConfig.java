package com.TeamProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/Student/main").setViewName("dashboardStu");
        registry.addViewController("/Professor/main").setViewName("dashboardProf");
        registry.addViewController("/Admin/main").setViewName("dashboardAdm");
        registry.addViewController("/404").setViewName("404Page");

    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/img/**","/css/*");
        registry.addInterceptor(new StudentLoginHandlerInterceptor()).addPathPatterns("/Student/main");
        //registry.addInterceptor(new ProfessorLoginHandlerInterceptor()).addPathPatterns("/Professor/main").excludePathPatterns();
        //registry.addInterceptor(new AdminLoginHandlerInterceptor()).addPathPatterns("/Admin/main");


    }
}
