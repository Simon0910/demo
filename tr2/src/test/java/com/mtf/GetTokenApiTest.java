package com.mtf;

import com.alibaba.fastjson.JSON;
import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.ApiException;
import com.tr.core.Environment;
import com.tr.core.api.GetTaskStatusApi;
import com.tr.core.api.GetTokenApi;
import com.tr.core.model.GetTaskStatusRequestSchema;
import com.tr.core.model.GetTaskStatusResponseSchema;
import com.tr.core.model.GetTokenRequestSchema;
import com.tr.core.model.GetTokenResponseSchema;
import org.junit.Test;

import java.util.UUID;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-09 15:29
 */
public class GetTokenApiTest {

    @Test
    public void getToken() throws ApiException {
        new ApiConfig();

        ApiClient client = new ApiClient();
        client.setDebugging(true)
                .setCurrentEnvironment(Environment.MTF);

        GetTokenApi api = new GetTokenApi(client);
        GetTokenRequestSchema requestSchema = new GetTokenRequestSchema();
        requestSchema.requestId(UUID.randomUUID().toString())
                .responseHost("pre-tr.cjdfintech.com/master")
                .paymentAppInstanceId("iYBVQzoZIuwVARezwT3spA--")
                .tokenUniqueReference("DCJDMC00001441364f3c9db87c6742c5af5754a73e07ed8f")
        ;
        GetTokenResponseSchema token = api.getToken(requestSchema);
        System.out.println(JSON.toJSONString(token));
    }
}
