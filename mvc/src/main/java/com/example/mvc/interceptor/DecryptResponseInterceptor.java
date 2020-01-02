package com.example.mvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 17:03
 */
@Component
public class DecryptResponseInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(">>>>>>>>");
        System.out.println(request);
        System.out.println(">>>>>>>>");
        System.out.println(response);
        return super.preHandle(request, response, handler);
    }
}
