package com.dfbz.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.dfbz.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
    /**
     * springmvc文件上传：
     * 配置springmvc文件上传解析器  multiparResolerver
     * 接收文件上传处理方法上添加 MultipartFile  接收请求数据
     */
    @Bean("multipartResolver")//必须指定bean命名
    public CommonsMultipartResolver getMultipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        return  commonsMultipartResolver;
    }

    @Override//放行静态资源
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
