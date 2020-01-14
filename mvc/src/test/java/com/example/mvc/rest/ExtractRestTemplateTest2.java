package com.example.mvc.rest;

import com.alibaba.fastjson.JSON;
import com.example.mvc.MvcApplicationTests;
import com.example.mvc.rest.mc.GetTokenRequestSchema;
import com.example.mvc.rest.mc.GetTokenResponseSchema;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.UUID;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-09 17:33
 */
public class ExtractRestTemplateTest2 extends MvcApplicationTests {

    @Autowired
    ExtractRestTemplate extractRestTemplate;

    @Test
    public void mcTest() {
        GetTokenRequestSchema requestSchema = new GetTokenRequestSchema();
        requestSchema.requestId(UUID.randomUUID().toString())
                .responseHost("pre-tr.cjdfintech.com/master")
                .paymentAppInstanceId("iYBVQzoZIuwVARezwT3spA--")
                .tokenUniqueReference("DCJDMC00001441364f3c9db87c6742c5af5754a73e07ed8f")
        ;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ApiResponse<GetTokenResponseSchema> exchange = extractRestTemplate.exchange(
                "https://mtf.services.mastercard.com/mtf/mdes/digitization/1/0/getToken",
                requestSchema,
                httpHeaders,
                HttpMethod.POST,
                GetTokenResponseSchema.class);

        System.out.println(" ==> " + JSON.toJSONString(exchange));
    }
}
