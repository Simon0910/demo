package com.example.mvc.controller;

import com.example.mvc.exception.EncryptionException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-25 12:45
 */
@RestController
@RequestMapping("/d02")
public class Demo02Controller {

    @PostMapping("/test")
    public void testGlobalExceptionOrderd() throws EncryptionException {
        System.out.println("ok");
        throw new EncryptionException("test", null);
    }

}
