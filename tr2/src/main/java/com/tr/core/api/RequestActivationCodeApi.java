package com.tr.core.api;

import com.google.gson.reflect.TypeToken;
import com.tr.core.*;
import com.tr.core.model.ActivationCodeRequestSchema;
import com.tr.core.model.ActivationCodeResponseSchema;
import com.tr.core.model.CheckEligibilityResponseSchema;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 10:27
 */
public class RequestActivationCodeApi {
    private ApiClient localVarApiClient;
    private OperationMetadata operationMetadata;
    private static OperationConfig operationConfig;

    public RequestActivationCodeApi() {
        this(ApiClientFactory.getDefaultApiClient());
    }

    public RequestActivationCodeApi(ApiClient apiClient) {
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
            operationConfig = new OperationConfig("/mdes/digitization/1/0/requestActivationCode", Arrays.asList(""),
                    Arrays.asList(""));
        }
    }

    public okhttp3.Call requestActivationCodeCall(ActivationCodeRequestSchema activeationCodeRequestSchema,
                                                  final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = activeationCodeRequestSchema;

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
    private okhttp3.Call requestActivationCodeValidateBeforeCall(ActivationCodeRequestSchema activeationCodeRequestSchema, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'assetId' is set
        if (activeationCodeRequestSchema == null) {
            throw new ApiException("Missing the required parameter 'assetId' when calling getAsset(Async)");
        }

        okhttp3.Call localVarCall = requestActivationCodeCall(activeationCodeRequestSchema, _callback);
        return localVarCall;
    }

    public ActivationCodeResponseSchema requestActivationCode(ActivationCodeRequestSchema activeationCodeRequestSchema) throws ApiException {
        ApiResponse<ActivationCodeResponseSchema> localVarResp =
                requestActivationCodeWithHttpInfo(activeationCodeRequestSchema);
        return localVarResp.getData();
    }

    public ApiResponse<ActivationCodeResponseSchema> requestActivationCodeWithHttpInfo(ActivationCodeRequestSchema activeationCodeRequestSchema) throws ApiException {
        okhttp3.Call localVarCall = requestActivationCodeValidateBeforeCall(activeationCodeRequestSchema, null);
        Type localVarReturnType = new TypeToken<ActivationCodeResponseSchema>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    public okhttp3.Call requestActivationCodeAsync(ActivationCodeRequestSchema activeationCodeRequestSchema,
                                                   final ApiCallback<ActivationCodeResponseSchema> _callback) throws ApiException {
        okhttp3.Call localVarCall = requestActivationCodeValidateBeforeCall(activeationCodeRequestSchema, _callback);
        Type localVarReturnType = new TypeToken<CheckEligibilityResponseSchema>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

}
