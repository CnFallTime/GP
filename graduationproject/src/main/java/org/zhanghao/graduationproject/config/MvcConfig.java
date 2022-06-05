package org.zhanghao.graduationproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zhanghao.graduationproject.component.LoginHandlerInterceptor;

/**
 * @author 张昊
 * @date 2019/9/11 - 20:18
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer WebMvcConfigurer(){
        WebMvcConfigurer wcg=new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("hello");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("home");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                        excludePathPatterns("/asserts/**","/","/index.html","/user/login","/user/zhuce","/logout");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
                WebMvcConfigurer.super.addResourceHandlers(registry);
            }
        };

        return wcg;
    }
}
