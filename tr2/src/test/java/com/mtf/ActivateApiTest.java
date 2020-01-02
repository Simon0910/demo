package com.mtf;

import com.alibaba.fastjson.JSON;
import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.ApiException;
import com.tr.core.Environment;
import com.tr.core.api.ActivateApi;
import com.tr.core.model.ActivateRequestSchema;
import com.tr.core.model.ActivateResponseSchema;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-27 17:35
 */
public class ActivateApiTest {
    public static void main(String[] args) throws ApiException {
        new ApiConfig();

        ApiClient client = new ApiClient();
        client.setDebugging(true)
                .setCurrentEnvironment(Environment.MTF);

        ActivateApi api = new ActivateApi(client);
        ActivateRequestSchema requestSchema = new ActivateRequestSchema()
                // .requestId(UUID.randomUUID().toString())
                .requestId("123456")
                .responseHost("pre-tr.cjdfintech.com/master")
                .paymentAppInstanceId("")
                .authenticationCode("")
                .tokenizationAuthenticationValue("")
                .tokenUniqueReference("");
        ActivateResponseSchema activate = api.activate(requestSchema);
        System.out.println(JSON.toJSONString(activate));
    }
}
