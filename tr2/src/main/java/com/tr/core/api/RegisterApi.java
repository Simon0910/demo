package com.tr.core.api;

import com.google.gson.reflect.TypeToken;
import com.tr.core.*;
import com.tr.core.model.DigitizeResponseSchema;
import com.tr.core.model.RegisterRequestSchema;
import com.tr.core.model.RegisterResponseSchema;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-21 16:29
 */
public class RegisterApi {
    private ApiClient localVarApiClient;
    private OperationMetadata operationMetadata;
    private static OperationConfig operationConfig;

    public RegisterApi() {
        this(ApiClientFactory.getDefaultApiClient());
    }

    public RegisterApi(ApiClient apiClient) {
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
            operationConfig = new OperationConfig("/mdes/mpamanagement/1/0/register", Arrays.asList(""), Arrays.asList(""));
        }
        return operationConfig;
    }

    public okhttp3.Call createRegisterCall(RegisterRequestSchema registerRequestSchema, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = registerRequestSchema;

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
    private okhttp3.Call createRegisterValidateBeforeCall(RegisterRequestSchema registerRequestSchema, final ApiCallback _callback) throws ApiException {
        okhttp3.Call localVarCall = createRegisterCall(registerRequestSchema, _callback);
        return localVarCall;
    }

    public RegisterResponseSchema register(RegisterRequestSchema registerRequestSchema) throws ApiException {
        ApiResponse<RegisterResponseSchema> localVarResp = registerWithHttpInfo(registerRequestSchema);
        return localVarResp.getData();
    }

    public ApiResponse<RegisterResponseSchema> registerWithHttpInfo(RegisterRequestSchema registerRequestSchema) throws ApiException {
        okhttp3.Call localVarCall = createRegisterValidateBeforeCall(registerRequestSchema, null);
        Type localVarReturnType = new TypeToken<RegisterResponseSchema>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    public okhttp3.Call registerAsync(RegisterRequestSchema registerRequestSchema, final ApiCallback<RegisterResponseSchema> _callback) throws ApiException {
        okhttp3.Call localVarCall = createRegisterValidateBeforeCall(registerRequestSchema, _callback);
        Type localVarReturnType = new TypeToken<RegisterResponseSchema>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
