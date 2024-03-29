/*
 * MDES for Merchants
 * The MDES APIs are designed as RPC style stateless web services where each API endpoint represents an operation to be performed.  All request and response payloads are sent in the JSON (JavaScript Object Notation) data-interchange format. Each endpoint in the API specifies the HTTP Method used to access it. All strings in request and response objects are to be UTF-8 encoded.  Each API URI includes the major and minor version of API that it conforms to.  This will allow multiple concurrent versions of the API to be deployed simultaneously. <br> __Authentication__ Mastercard uses OAuth 1.0a with body hash extension for authenticating the API clients. This requires every request that you send to  Mastercard to be signed with an RSA private key. A private-public RSA key pair must be generated consisting of: <br> 1 . A private key for the OAuth signature for API requests. It is recommended to keep the private key in a password-protected or hardware keystore. <br> 2. A public key is shared with Mastercard during the project setup process through either a certificate signing request (CSR) or the API Key Generator. Mastercard will use the public key to verify the OAuth signature that is provided on every API call.<br>  An OAUTH1.0a signer library is available on [GitHub](https://github.com/Mastercard/oauth1-signer-java) <br>  __Encryption__<br>  All communications between Issuer web service and the Mastercard gateway is encrypted using TLS. <br> __Additional Encryption of Sensitive Data__ In addition to the OAuth authentication, when using MDES Digital Enablement Service, any PCI sensitive and all account holder Personally Identifiable Information (PII) data must be encrypted. This requirement applies to the API fields containing encryptedData. Sensitive data is encrypted using a symmetric session (one-time-use) key. The symmetric session key is then wrapped with an RSA Public Key supplied by Mastercard during API setup phase (the Customer Encryption Key). <br>  Java Client Encryption Library available on [GitHub](https://github.com/Mastercard/client-encryption-java)
 *
 * The version of the OpenAPI document: 1.2.9
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.tr.core.api;

import com.google.gson.reflect.TypeToken;
import com.tr.core.*;
import com.tr.core.model.DeleteRequestSchema;
import com.tr.core.model.DeleteResponseSchema;

import java.lang.reflect.Type;
import java.util.*;

public class DeleteApi {
    private ApiClient localVarApiClient;
    private OperationMetadata operationMetadata;
    private static OperationConfig operationConfig;

    public DeleteApi() {
        this(ApiClientFactory.getDefaultApiClient());
    }

    public DeleteApi(ApiClient apiClient) {
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
            operationConfig = new OperationConfig("/mdes/digitization/1/0/delete", Arrays.asList(""), Arrays.asList(""));
        }
    }

    /**
     * Build call for deleteDigitization
     *
     * @param deleteRequestSchema Contains the details of the request message.  (optional)
     * @param _callback           Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the response message.  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> unexpected error  </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call deleteDigitizationCall(DeleteRequestSchema deleteRequestSchema, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = deleteRequestSchema;

        // create path and map variables
        String localVarPath = "/mdes/digitization/1/0/delete";

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
    private okhttp3.Call deleteDigitizationValidateBeforeCall(DeleteRequestSchema deleteRequestSchema, final ApiCallback _callback) throws ApiException {


        okhttp3.Call localVarCall = deleteDigitizationCall(deleteRequestSchema, _callback);
        return localVarCall;

    }

    /**
     * Used to delete one or more Tokens. The API is limited to 10 Tokens per request.
     * This API is used to delete one or more Tokens.  The API is limited to 10 Tokens per request. MDES will coordinate the deactivation of the Tokens and notify any relevant parties that the Tokens have now been deactivated.
     *
     * @param deleteRequestSchema Contains the details of the request message.  (optional)
     * @return DeleteResponseSchema
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the response message.  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> unexpected error  </td><td>  -  </td></tr>
     * </table>
     */
    public DeleteResponseSchema deleteDigitization(DeleteRequestSchema deleteRequestSchema) throws ApiException {
        ApiResponse<DeleteResponseSchema> localVarResp = deleteDigitizationWithHttpInfo(deleteRequestSchema);
        return localVarResp.getData();
    }

    /**
     * Used to delete one or more Tokens. The API is limited to 10 Tokens per request.
     * This API is used to delete one or more Tokens.  The API is limited to 10 Tokens per request. MDES will coordinate the deactivation of the Tokens and notify any relevant parties that the Tokens have now been deactivated.
     *
     * @param deleteRequestSchema Contains the details of the request message.  (optional)
     * @return ApiResponse&lt;DeleteResponseSchema&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the response message.  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> unexpected error  </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<DeleteResponseSchema> deleteDigitizationWithHttpInfo(DeleteRequestSchema deleteRequestSchema) throws ApiException {
        okhttp3.Call localVarCall = deleteDigitizationValidateBeforeCall(deleteRequestSchema, null);
        Type localVarReturnType = new TypeToken<DeleteResponseSchema>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Used to delete one or more Tokens. The API is limited to 10 Tokens per request. (asynchronously)
     * This API is used to delete one or more Tokens.  The API is limited to 10 Tokens per request. MDES will coordinate the deactivation of the Tokens and notify any relevant parties that the Tokens have now been deactivated.
     *
     * @param deleteRequestSchema Contains the details of the request message.  (optional)
     * @param _callback           The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the response message.  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> unexpected error  </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call deleteDigitizationAsync(DeleteRequestSchema deleteRequestSchema, final ApiCallback<DeleteResponseSchema> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteDigitizationValidateBeforeCall(deleteRequestSchema, _callback);
        Type localVarReturnType = new TypeToken<DeleteResponseSchema>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
