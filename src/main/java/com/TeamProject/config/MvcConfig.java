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

        //Admin Course
        registry.addViewController("/Admin/Courses").setViewName("coursesAdm");
        registry.addViewController("/Admin/Courses/Create").setViewName("createCourseAdmin");
        //👇 In AdminDeleteCourseController
        registry.addViewController("/Admin/Courses/Delete").setViewName("deleteCourseAdmin");
        //Admin Person
        registry.addViewController("/Admin/Person").setViewName("404Page");
        registry.addViewController("/Admin/Person/Create").setViewName("createPersonAdmin");
        registry.addViewController("/Admin/Person/Delete").setViewName("deletePersonAdmin");
        //Student Course
        registry.addViewController("/Admin/Person").setViewName("404Page");
//        registry.addViewController("/Student/Register/").setViewName("RegisterStu");


        //prof deliver
       // registry.addViewController("/Professor/Deliver/Delete").setViewName("deletedeliverableProf");
         registry.addViewController("/Professor/Grades").setViewName("gradeProf");
        registry.addViewController("/Professor/Deliverable").setViewName("deliverableProf");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/img/**","/css/*","/javascript/**","/Apply");
       registry.addInterceptor(new StudentLoginHandlerInterceptor()).addPathPatterns("/Student/**");
       registry.addInterceptor(new ProfessorLoginHandlerInterceptor()).addPathPatterns("/Professor/**");
       registry.addInterceptor(new AdminLoginHandlerInterceptor()).addPathPatterns("/Admin/**");


    }
}
