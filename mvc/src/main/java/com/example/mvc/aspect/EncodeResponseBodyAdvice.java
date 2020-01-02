package com.example.mvc.aspect;

import com.example.mvc.utils.Base64Utils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Component
@ControllerAdvice(basePackages = "com.example.mvc.controller")
@Slf4j
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                    ServerHttpResponse response) {
        System.out.println("response-------->"+body);
        //加密
        log.info("【接受到的数据】encode={}",body);
        String encode = Base64Utils.encode(JSON.toJSONString(body));
        return encode;
    }

}