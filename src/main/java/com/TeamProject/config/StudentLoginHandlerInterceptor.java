package com.TeamProject.config;

import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentLoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //login success
        String loginP = (String)request.getSession().getAttribute("loginP");

        if(!loginP.equals("Student")){
            response.sendRedirect("/404");
            return false;
        }else{
            return true;
        }
    }


}
