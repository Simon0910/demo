package com.lzp.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.lzp.demo.mapper", "com.lzp.demo.dao"})
public class MybatisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisServiceApplication.class, args);
    }

}
