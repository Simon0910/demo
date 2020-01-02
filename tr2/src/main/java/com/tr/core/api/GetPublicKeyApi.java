package com.tr.core.api;

import com.google.gson.reflect.TypeToken;
import com.tr.core.*;
import com.tr.core.model.AssetResponseSchema;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-13 14:03
 */
public class GetPublicKeyApi {
    private ApiClient localVarApiClient;
    private OperationMetadata operationMetadata;
    private static OperationConfig operationConfig;

    public GetPublicKeyApi() {
        this(ApiClientFactory.getDefaultApiClient());
    }

    public GetPublicKeyApi(ApiClient apiClient) {
        initEnv(apiClient);
        initOperationConfig();
        localVarApiClient = apiClient;
        localVarApiClient.setOperationMetadata(operationMetadata);
    }

    private void initEnv(ApiClient apiClient) {
        ResourceConfig instance = ResourceConfig.getInstance(apiClient);
        this.operationMetadata = new OperationMetadata(instance.getVersion(), instance.getHost(), instance.getContext(), instance.getJsonNative());
    }

    private void initOperationConfig() {
        if (operationConfig == null) {
            operationConfig = new OperationConfig("/mdes/mpamanagement/1/0/pkCertificate", Arrays.asList(""), Arrays.asList(""));
        }
    }

    public okhttp3.Call createPublicKeyCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath = operationConfig.getResourcePath();

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
                "application/pkix-cert"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[]{};
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    public String getPublicKey() throws ApiException {
        ApiResponse<String> localVarResp = getPublicKeyWithHttpInfo();
        return localVarResp.getData();
    }

    public ApiResponse<String> getPublicKeyWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = createPublicKeyCall(null);
        Type localVarReturnType = new TypeToken<String>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

}
