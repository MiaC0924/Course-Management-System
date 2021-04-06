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
        registry.addViewController("/testD").setViewName("dashboard");
        registry.addViewController("/Admin/Courses").setViewName("coursesAdm");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/img/**","/css/*","/javascript/**");
       registry.addInterceptor(new StudentLoginHandlerInterceptor()).addPathPatterns("/Student/**");
       registry.addInterceptor(new ProfessorLoginHandlerInterceptor()).addPathPatterns("/Professor/**");
       registry.addInterceptor(new AdminLoginHandlerInterceptor()).addPathPatterns("/Admin/**");


    }
}
