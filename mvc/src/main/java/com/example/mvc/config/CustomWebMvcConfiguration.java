package com.example.mvc.config;

import com.example.mvc.interceptor.DecryptResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 17:26
 */
@Configuration
public class CustomWebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    DecryptResponseInterceptor decryptRespornseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(decryptRespornseInterceptor).addPathPatterns("/master/notify");
        super.addInterceptors(registry);
    }
}
