package com.indielist.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jsingh on 15-01-17.
 */
public class ControllerProfiler implements HandlerInterceptor {

    public static final String DURATION_ATTRIBUTE = "method.duration";
    private static final Logger log = LogManager.getLogger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(DURATION_ATTRIBUTE, System.nanoTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            if(handler instanceof HandlerMethod) {
                long endTime = System.nanoTime();
                long startTime = (long) request.getAttribute(DURATION_ATTRIBUTE);
                String method = ((HandlerMethod) handler).getMethod().getName();
                log.info("Performance: " + method + ": " + ((int) ((endTime - startTime) / 1e6)) + ": ms");
            }
        } catch (Exception e) {
            log.warn("Failed to calculate profiling data for current request", e);
        }
    }
}

