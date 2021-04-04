package com.TeamProject.config;

import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //login success
        String loginUser = (String)request.getSession().getAttribute("loginUser");

        if(loginUser == null){
            request.setAttribute("msg", "Please login first");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            return true;
        }
    }


}
