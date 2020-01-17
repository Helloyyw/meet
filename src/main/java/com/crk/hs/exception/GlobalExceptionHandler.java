package com.crk.hs.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //@ControllerAdvice 该注解定义全局异常处理类
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class) //@ExceptionHandler 该注解声明异常处理方法
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);  //异常内容(页面展示)
        mav.addObject("url", req.getRequestURL()); //请求的url地址(页面展示)
        mav.setViewName("404"); //设置视图名称
        return mav;
    }
}