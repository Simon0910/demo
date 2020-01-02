package com.tr.core.api;

import com.google.gson.reflect.TypeToken;
import com.tr.core.*;
import com.tr.core.model.CheckEligibilityRequestSchema;
import com.tr.core.model.CheckEligibilityResponseSchema;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 09:54
 */
public class CheckEligibilityApi {
    private ApiClient localVarApiClient;
    private OperationMetadata operationMetadata;
    private static OperationConfig operationConfig;

    public CheckEligibilityApi() {
        this(ApiClientFactory.getDefaultApiClient());
    }

    public CheckEligibilityApi(ApiClient apiClient) {
        initEnv(apiClient);
        initOperationConfig();
        localVarApiClient = apiClient;
        localVarApiClient.setOperationMetadata(operationMetadata);
    }

    private void initEnv(ApiClient apiClient) {
        ResourceConfig instance = ResourceConfig.getInstance(apiClient);
        this.operationMetadata = new OperationMetadata(instance.getVersion(), instance.getHost(), instance.getContext(), instance.getJsonNative());
    }

    private OperationConfig initOperationConfig() {
        if (operationConfig == null) {
            operationConfig = new OperationConfig("/mdes/digitization/1/0/checkEligibility", Arrays.asList(""), Arrays.asList(""));
        }
        return operationConfig;
    }

    public okhttp3.Call createCheckEligibilityCall(CheckEligibilityRequestSchema checkEligibilityRequestSchema, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = checkEligibilityRequestSchema;

        // create path and map variables
        String localVarPath = operationConfig.getResourcePath();

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[]{};
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createCheckEligibilityValidateBeforeCall(CheckEligibilityRequestSchema checkEligibilityRequestSchema, final ApiCallback _callback) throws ApiException {
        okhttp3.Call localVarCall = createCheckEligibilityCall(checkEligibilityRequestSchema, _callback);
        return localVarCall;
    }

    public CheckEligibilityResponseSchema checkEligibility(CheckEligibilityRequestSchema checkEligibilityRequestSchema) throws ApiException {
        ApiResponse<CheckEligibilityResponseSchema> localVarResp = checkEligibilityWithHttpInfo(checkEligibilityRequestSchema);
        return localVarResp.getData();
    }

    public ApiResponse<CheckEligibilityResponseSchema> checkEligibilityWithHttpInfo(CheckEligibilityRequestSchema checkEligibilityRequestSchema) throws ApiException {
        okhttp3.Call localVarCall = createCheckEligibilityValidateBeforeCall(checkEligibilityRequestSchema, null);
        Type localVarReturnType = new TypeToken<CheckEligibilityResponseSchema>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    public okhttp3.Call checkEligibilityAsync(CheckEligibilityRequestSchema checkEligibilityRequestSchema, final ApiCallback<CheckEligibilityResponseSchema> _callback) throws ApiException {
        okhttp3.Call localVarCall = createCheckEligibilityValidateBeforeCall(checkEligibilityRequestSchema, _callback);
        Type localVarReturnType = new TypeToken<CheckEligibilityResponseSchema>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
