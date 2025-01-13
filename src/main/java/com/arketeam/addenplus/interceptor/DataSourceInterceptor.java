package com.arketeam.addenplus.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.arketeam.addenplus.constant.DataSourceType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class DataSourceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String contextPath = request.getServletContext().getContextPath();
        String prefixPool1 = contextPath + "/pool1";
        String prefixPool2 = contextPath + "/pool2";
        String uri = request.getRequestURI();
        System.out.println("URI:" + uri);

        if (uri.contains(prefixPool1)) {
            request.setAttribute("database", DataSourceType.POOL1);
        } else if (uri.contains(prefixPool2)) {
            request.setAttribute("database", DataSourceType.POOL2);
        }
        return true;
    }
}
