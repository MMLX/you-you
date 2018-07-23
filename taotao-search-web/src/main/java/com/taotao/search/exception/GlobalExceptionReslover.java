package com.taotao.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionReslover implements HandlerExceptionResolver{
    //创建logger日志对象
    Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        /**
         *  做两个事情
         *  1.展示一个友好的页面
         *  2.记录日志
         */
        //使用对象去记录日志
        logger.error("报错了",ex);
        ModelAndView modelAndView = new ModelAndView();
        //提示保存信息
        modelAndView.addObject("message", "系统发生异常，请稍后重试");
        //跳转的页面
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
