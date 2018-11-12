package com.wgh.springboot.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/***
 * @Desciption: 拦截器
 * @Date: 13:53 2018/1/15
 * @return
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WghInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/to/login")
                .excludePathPatterns("/sys/login").excludePathPatterns("/to/home");
    }
}