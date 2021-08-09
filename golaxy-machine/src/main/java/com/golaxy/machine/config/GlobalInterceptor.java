package com.golaxy.machine.config;

import com.alibaba.fastjson.JSON;
import com.golaxy.machine.util.JsonResult;
import com.golaxy.machine.util.JwtUtils;
import com.golaxy.machine.util.UtilsApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * @author miaoxuebing
 * @Description: TODO[全局的拦截器，如果是登陆接口可以不用校验，如果是其他接口需要检验接口token]
 * @date 2021/1/30 下午10:40
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {
    /**
     * 日志对象
     **/
    private final static Logger log = LoggerFactory.getLogger(GlobalInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        //获取请求路径
        //String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + request.getContextPath() + "/";
        String basePath = request.getRequestURI();
        System.out.println("请求URL: ".concat(basePath));
        System.out.println("token: " + request.getHeader("token"));
        //获取请求中的token
        String accessToken = getAuthToken(request);
        if (StringUtils.isBlank(accessToken)) {
            setResponse(response, false,"请求token为空，请检查！");
            return false;
        }
        //如果token不为空，需要Jwt解析token校验
        try {
            String userId = JwtUtils.analysisJwtToken(accessToken);
            if(UtilsApi.isNull(userId)){
                setResponse(response, false,"请求token异常，请检查！");
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            setResponse(response,false,"登陆失效，请重新登录！");
        }
        return true;
    }

    /**
    * @Description: 获取请求中的token，默认从头部Header中取，如果没有就从request参数中取
    * @Params: [request]
    * @Return: java.lang.String
    * @Author: miaoxuebing
    * @Date: 2021/7/16 下午2:52
    **/
    private String getAuthToken(HttpServletRequest request) {
        String token = request.getHeader("token");

        if (token == null) {
            token = request.getParameter("token");
        }
        return token;
    }

    /**
    * @Description: 返回结果封装
    * @Params: [request, response, flag, message]
    * @Return: void
    * @Author: miaoxuebing
    * @Date: 2021/7/16 下午2:57
    **/
    public void setResponse(HttpServletResponse response, boolean flag, String message) {
        JsonResult res = null;
        //设置返回格式
        response.setContentType("application/json;charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            if(flag == false){
                res = new JsonResult(JsonResult.FAIL,message);
            }
            JSON.writeJSONString(writer, res);
            writer.flush();
        } catch (IOException e) {
            log.error("respose 设置操作异常：" + e);
        }
    }

}
