package com.mtf;

import com.alibaba.fastjson.JSON;
import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.ApiException;
import com.tr.core.Environment;
import com.tr.core.api.GetTaskStatusApi;
import com.tr.core.model.GetTaskStatusRequestSchema;
import com.tr.core.model.GetTaskStatusResponseSchema;

import java.util.UUID;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-27 16:58
 */
public class GetTaskStatusApiTest {
    public static void main(String[] args) throws ApiException {
        new ApiConfig();

        ApiClient client = new ApiClient();
        client.setDebugging(true)
                .setCurrentEnvironment(Environment.MTF);

        GetTaskStatusApi api = new GetTaskStatusApi(client);
        GetTaskStatusRequestSchema requestSchema = new GetTaskStatusRequestSchema();
        requestSchema.requestId(UUID.randomUUID().toString())
                .responseHost("pre-tr.cjdfintech.com/master")
                .paymentAppInstanceId("jQCG9s7h3TWw9yHIOwUoSQ--")
                .taskId("jQCG9s7h3TWw9yHIOwUoSQ")
                // .tokenRequestorId("")
                ;
        GetTaskStatusResponseSchema taskStatus = api.getTaskStatus(requestSchema);
        System.out.println(JSON.toJSONString(taskStatus));
    }
}
