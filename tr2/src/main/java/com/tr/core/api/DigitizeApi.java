package com.tr.core.api;

import com.google.gson.reflect.TypeToken;
import com.tr.core.*;
import com.tr.core.model.DigitizeRequestSchema;
import com.tr.core.model.DigitizeResponseSchema;
import com.tr.core.model.TokenizeRequestSchema;
import com.tr.core.model.TokenizeResponseSchema;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-21 11:36
 */
public class DigitizeApi {
    private ApiClient localVarApiClient;
    private OperationMetadata operationMetadata;
    private static OperationConfig operationConfig;

    public DigitizeApi() {
        this(ApiClientFactory.getDefaultApiClient());
    }

    public DigitizeApi(ApiClient apiClient) {
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
            operationConfig = new OperationConfig("/mdes/digitization/1/0/digitize", Arrays.asList(""), Arrays.asList(""));
        }
        return operationConfig;
    }

    /**
     *
     * @param digitizeRequestSchema A Digitize request is used to digitize a PAN.   (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the token response message when a push account receipt is supplied in the digitize request.  </td><td>  -  </td></tr>
     * <tr><td> 500 </td><td> unexpected error  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> Contains the details of the response message when a push account receipt is not supplied in the digitize request.  </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call createDigitizeCall(DigitizeRequestSchema digitizeRequestSchema, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = digitizeRequestSchema;

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
    private okhttp3.Call createDigitizeValidateBeforeCall(DigitizeRequestSchema digitizeRequestSchema, final ApiCallback _callback) throws ApiException {
        okhttp3.Call localVarCall = createDigitizeCall(digitizeRequestSchema, _callback);
        return localVarCall;
    }

    public DigitizeResponseSchema createDigitize(DigitizeRequestSchema digitizeRequestSchema) throws ApiException {
        ApiResponse<DigitizeResponseSchema> localVarResp = createDigitizeWithHttpInfo(digitizeRequestSchema);
        return localVarResp.getData();
    }


    public ApiResponse<DigitizeResponseSchema> createDigitizeWithHttpInfo(DigitizeRequestSchema digitizeRequestSchema) throws ApiException {
        okhttp3.Call localVarCall = createDigitizeValidateBeforeCall(digitizeRequestSchema, null);
        Type localVarReturnType = new TypeToken<DigitizeResponseSchema>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }


    public okhttp3.Call createDigitizeAsync(DigitizeRequestSchema digitizeRequestSchema, final ApiCallback<TokenizeResponseSchema> _callback) throws ApiException {

        okhttp3.Call localVarCall = createDigitizeValidateBeforeCall(digitizeRequestSchema, _callback);
        Type localVarReturnType = new TypeToken<TokenizeResponseSchema>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
