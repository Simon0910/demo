package com.tr.core.api;

import com.google.gson.reflect.TypeToken;
import com.tr.core.*;
import com.tr.core.model.ActivateRequestSchema;
import com.tr.core.model.ActivateResponseSchema;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 11:16
 */
public class ActivateApi {
    private ApiClient localVarApiClient;
    private OperationMetadata operationMetadata;
    private static OperationConfig operationConfig;

    public ActivateApi() {
        this(ApiClientFactory.getDefaultApiClient());
    }

    public ActivateApi(ApiClient apiClient) {
        initEnv(apiClient);
        initOperationConfig();
        localVarApiClient = apiClient;
        localVarApiClient.setOperationMetadata(operationMetadata);
    }

    private void initEnv(ApiClient apiClient) {
        ResourceConfig instance = ResourceConfig.getInstance(apiClient);
        this.operationMetadata = new OperationMetadata(instance.getVersion(), instance.getHost(),
                instance.getContext(), instance.getJsonNative());
    }

    private void initOperationConfig() {
        if (operationConfig == null) {
            operationConfig = new OperationConfig("/mdes/digitization/1/0/activate", Arrays.asList(""),
                    Arrays.asList(""));
        }
    }

    public okhttp3.Call activateCall(ActivateRequestSchema activateRequestSchema,
                                     final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = activateRequestSchema;

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

        final String[] localVarContentTypes = {};
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[]{};
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
                localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call activateValidateBeforeCall(ActivateRequestSchema activateRequestSchema,
                                                    final ApiCallback _callback) throws ApiException {
        okhttp3.Call localVarCall = activateCall(activateRequestSchema, _callback);
        return localVarCall;
    }

    public ActivateResponseSchema activate(ActivateRequestSchema activateRequestSchema) throws ApiException {
        ApiResponse<ActivateResponseSchema> localVarResp = activateWithHttpInfo(activateRequestSchema);
        return localVarResp.getData();
    }

    public ApiResponse<ActivateResponseSchema> activateWithHttpInfo(ActivateRequestSchema activateRequestSchema) throws ApiException {
        okhttp3.Call localVarCall = activateValidateBeforeCall(activateRequestSchema, null);
        Type localVarReturnType = new TypeToken<ActivateResponseSchema>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    public okhttp3.Call requestActivationCodeAsync(ActivateRequestSchema activateRequestSchema,
                                                   final ApiCallback<ActivateResponseSchema> _callback) throws ApiException {
        okhttp3.Call localVarCall = activateValidateBeforeCall(activateRequestSchema, _callback);
        Type localVarReturnType = new TypeToken<ActivateResponseSchema>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
