package com.example.mvc.resolver;

import com.example.mvc.exception.EncryptionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by daniel on 2019/7/25 13:57.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionResolver {
    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        log.error("Exception={},{}", e.getMessage(), e);
        return e.getMessage();
    }


    @ExceptionHandler(EncryptionException.class)
    @ResponseBody
    public String handleEncryptionException(EncryptionException e) {
        log.error("EncryptionException={},{}", e.getMessage(), e);
        return e.getMessage();
    }


}


