package com.example.mvc;

import com.example.mvc.rest.ExtractRestTemplate;
import com.example.mvc.rest.RestResponseDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MvcApplicationTests {

    @Autowired
    ExtractRestTemplate extractRestTemplate;

    @Test
    public void contextLoads() {
        RestResponseDTO<String> exchange = extractRestTemplate.exchange("https://www.baidu.com/", null, String.class);
        System.out.println(exchange.getData());
    }

}
