package com.hrmanager.hr_manager.Manager.config;


import com.hrmanager.hr_manager.Manager.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
//@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {

    //视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String prefix = "index/";
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/index.html").setViewName(prefix +"index");
        registry.addViewController("/welcome.html").setViewName(prefix +"welcome");
        registry.addViewController("/query-user.html").setViewName(prefix +"query-user");
        registry.addViewController("/add-user.html").setViewName(prefix +"add-user");
        registry.addViewController("/add-dept.html").setViewName(prefix +"add-dept");
        registry.addViewController("/add-work.html").setViewName(prefix +"add-work");
        registry.addViewController("/add-role.html").setViewName(prefix +"add-role");
        registry.addViewController("/add-worker.html").setViewName(prefix +"add-worker");
        registry.addViewController("/add-announce.html").setViewName(prefix +"add-announce");
        registry.addViewController("/upload.html").setViewName(prefix +"upload");

    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/login.html","/register.html","/user/login");
    }


}

