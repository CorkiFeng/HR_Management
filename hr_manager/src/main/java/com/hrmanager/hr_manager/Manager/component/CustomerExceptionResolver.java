package com.hrmanager.hr_manager.Manager.component;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        if (ex instanceof MessageException){
            MessageException me = (MessageException) ex;
            modelAndView.addObject("error/404",me.getMessage());
        }else{
            modelAndView.addObject("error/404","未知异常！！！");
        }
        modelAndView.setViewName("error/404");
        return modelAndView;
    }
}
