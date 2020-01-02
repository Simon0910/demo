package com.mtf;

import com.alibaba.fastjson.JSON;
import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.ApiException;
import com.tr.core.Environment;
import com.tr.core.api.RequestActivationCodeApi;
import com.tr.core.model.ActivationCodeRequestSchema;
import com.tr.core.model.ActivationCodeResponseSchema;
import com.tr.core.model.AuthenticationMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-27 17:34
 */
@Slf4j
public class RequestActivationCodeApiTest {
    public static void main(String[] args) throws ApiException {
        new ApiConfig();

        ApiClient client = new ApiClient();
        client.setDebugging(true)
                .setCurrentEnvironment(Environment.MTF);

        RequestActivationCodeApi api = new RequestActivationCodeApi(client);
        ActivationCodeRequestSchema activationCodeRequestSchema =
                new ActivationCodeRequestSchema()
                .requestId("123456")
                .responseHost("pre-tr.cjdfintech.com/master")
                // .paymentAppInstanceId("iYBVQzoZIuwVARezwT3spB--")
                .paymentAppInstanceId("jRDvvlbArHDYKWtRLni2AA--")
                .authenticationMethod(new AuthenticationMethod()
                        .id("5990000000000001")
                        // .type("CARDHOLDER_TO_CALL_MANNED_NUMBER")
                        // .value("8675309")
                        )
                // .tokenUniqueReference("DCJDMC000014413692459cde8a57450a92aa9978dbb3e143")
                .tokenUniqueReference("DCJDMC000014413664930eed35e14de4ad9e3a6f4f576e54")
                ;
        log.info("requestActivationCode from mastercard,request={}", JSON.toJSONString(activationCodeRequestSchema));
        ActivationCodeResponseSchema activationCodeResponseSchema = api.requestActivationCode(activationCodeRequestSchema);
        log.info("requestActivationCode from mastercard,response={}", JSON.toJSONString(activationCodeResponseSchema));
       // System.out.println(JSON.toJSONString(activationCodeResponseSchema));
    }
}
