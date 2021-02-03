package com.golaxy.machine.miaoshao.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/30 下午10:40
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("请求URL: ".concat(request.getRequestURI()));
        System.out.println("token: " + request.getHeader("token"));
        return true;
    }

}
