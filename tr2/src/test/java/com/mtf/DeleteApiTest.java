package com.mtf;

import com.alibaba.fastjson.JSON;
import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.ApiException;
import com.tr.core.Environment;
import com.tr.core.api.DeleteApi;
import com.tr.core.enums.DeleteCausedByEnum;
import com.tr.core.model.DeleteRequestSchema;
import com.tr.core.model.DeleteResponseSchema;

import java.util.Arrays;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-06 16:54
 */
public class DeleteApiTest {

    /**
     * {"requestId":"123456","paymentAppInstanceId":"EC5B8dwokDBK2aMgGUDYwQ--",
     * "tokenUniqueReferences":["DCJDMC0000144136abda14bd09324e92931fb94719481c2f"],"causedBy":"CARDHOLDER"}
     * <p>
     * <p>
     * {"responseId":"123456","responseHost":"stl.services.mastercard.com/mtf/mdes","tokens":[{"tokenUniqueReference
     * ":"DCJDMC0000144136abda14bd09324e92931fb94719481c2f","status":"DEACTIVATED",
     * "statusTimestamp":"2019-12-06T10:13:52.526Z"}]}
     *
     * @param args
     * @throws ApiException
     */
    public static void main(String[] args) throws ApiException {
        new ApiConfig();
        ApiClient client = new ApiClient();
        client.setCurrentEnvironment(Environment.MTF)
                .setDebugging(true)
        ;
        DeleteApi api = new DeleteApi(client);

        DeleteRequestSchema deleteRequestSchema = new DeleteRequestSchema();
        // valid 1
        deleteRequestSchema.setRequestId("123456");
        // deleteRequestSchema.setResponseHost("pre-tr.cjdfintech.com/master");
        // valid 4
        deleteRequestSchema.setPaymentAppInstanceId("EC5B8dwokDBK2aMgGUDYwQ--");
        // valid 2
        deleteRequestSchema.setTokenUniqueReferences(Arrays.asList("DCJDMC0000144136abda14bd09324e92931fb94719481c2f"));
        // valid 3
        deleteRequestSchema.setCausedBy(DeleteCausedByEnum.CARDHOLDER.name());
        // deleteRequestSchema.setReason("DEVICE_LOST");
        // deleteRequestSchema.setReasonCode(DeleteReasonCodeEnum.DEVICE_LOST.name());

        DeleteResponseSchema deleteResponseSchema =
                api.deleteDigitization(deleteRequestSchema);
        System.out.println(JSON.toJSONString(deleteResponseSchema));
    }
}
