package com.beyond.hello.spring.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    /*
     * 不用通过Controller，画面端可以直接跳转的页面的配置
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/top").setViewName("top");
        registry.addViewController("/file-upload-status").setViewName("file-upload-status");
        registry.addViewController("/ajax-test").setViewName("ajax-test");

    }
}
