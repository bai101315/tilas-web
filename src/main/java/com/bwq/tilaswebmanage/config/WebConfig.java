package com.bwq.tilaswebmanage.config;

import com.bwq.tilaswebmanage.filter.LoginCheckFilter;
import com.bwq.tilaswebmanage.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration // 表明这是一个配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**");
    }

}
