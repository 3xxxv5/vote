package com.winterice.vote.interceptor;


import com.winterice.vote.annotation.Auth;
import com.winterice.vote.exception.UnloginException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行预检请求
        String method = request.getMethod();
        if(HttpMethod.OPTIONS.matches(method)){
            return true;
        }
        //检查注解
        HandlerMethod hand = (HandlerMethod) handler;
        Auth auth = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getMethod(), Auth.class);
        //无需鉴权，直接放行
        if (auth == null) {
            return true;
        }
        String target = auth.value();
        //鉴权逻辑
        if(checkRole(request, hand, target)){
            return true;
        }else{
            throw new UnloginException("您未登录或权限不足");
        }
    }

    private boolean checkRole(HttpServletRequest request, HandlerMethod hand, String target) throws Exception{
        String role = request.getHeader("role");
        if(Objects.equals(target, role)){
            return true;
        }else {
            return false;
        }
    }
}


