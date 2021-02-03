package com.golaxy.machine.miaoshao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author miaoxuebing
 * @Description: TODO
 * @date 2021/1/30 下午10:40
 */
@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    /**
    * @Description: 重写添加拦截器防范
    * @Params: [registry]
    * @Return: void
    * @Author: miaoxuebing
    * @Date: 2021/1/30 下午10:41
    **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getGlobalInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/manage/login",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",
                        "/error/**",
                        "/static/default/**")
                .order(1);
    }

    @Bean
    public GlobalInterceptor getGlobalInterceptor(){
        return new GlobalInterceptor();
    }
}
