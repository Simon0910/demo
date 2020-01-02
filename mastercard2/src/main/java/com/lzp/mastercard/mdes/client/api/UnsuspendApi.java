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


package com.lzp.mastercard.mdes.client.api;

import com.google.gson.reflect.TypeToken;
import com.lzp.mastercard.mdes.client.*;
import com.lzp.mastercard.mdes.client.model.UnSuspendRequestSchema;
import com.lzp.mastercard.mdes.client.model.UnSuspendResponseSchema;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnsuspendApi {
    private ApiClient localVarApiClient;

    public UnsuspendApi() {
        this(Configuration.getDefaultApiClient());
    }

    public UnsuspendApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for createUnsuspend
     *
     * @param unsuspendRequestSchema Contains the details of the request message.  (optional)
     * @param _callback              Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the response message.  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> unexpected error  </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call createUnsuspendCall(UnSuspendRequestSchema unsuspendRequestSchema, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = unsuspendRequestSchema;

        // create path and map variables
        String localVarPath = "/digitization/static/1/0/unsuspend";

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
    private okhttp3.Call createUnsuspendValidateBeforeCall(UnSuspendRequestSchema unsuspendRequestSchema, final ApiCallback _callback) throws ApiException {


        okhttp3.Call localVarCall = createUnsuspendCall(unsuspendRequestSchema, _callback);
        return localVarCall;

    }

    /**
     * Used to unsuspend one or more previously suspended Tokens. The API is limited to 10 Tokens per request.
     * This API is used to unsuspend one or more previously suspended Tokens. The API is limited to 10 Tokens per request. MDES will coordinate the unsuspension of the Tokens and notify any relevant parties that the Tokens have now been unsuspended.
     *
     * @param unsuspendRequestSchema Contains the details of the request message.  (optional)
     * @return UnSuspendResponseSchema
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the response message.  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> unexpected error  </td><td>  -  </td></tr>
     * </table>
     */
    public UnSuspendResponseSchema createUnsuspend(UnSuspendRequestSchema unsuspendRequestSchema) throws ApiException {
        ApiResponse<UnSuspendResponseSchema> localVarResp = createUnsuspendWithHttpInfo(unsuspendRequestSchema);
        return localVarResp.getData();
    }

    /**
     * Used to unsuspend one or more previously suspended Tokens. The API is limited to 10 Tokens per request.
     * This API is used to unsuspend one or more previously suspended Tokens. The API is limited to 10 Tokens per request. MDES will coordinate the unsuspension of the Tokens and notify any relevant parties that the Tokens have now been unsuspended.
     *
     * @param unsuspendRequestSchema Contains the details of the request message.  (optional)
     * @return ApiResponse&lt;UnSuspendResponseSchema&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the response message.  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> unexpected error  </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<UnSuspendResponseSchema> createUnsuspendWithHttpInfo(UnSuspendRequestSchema unsuspendRequestSchema) throws ApiException {
        okhttp3.Call localVarCall = createUnsuspendValidateBeforeCall(unsuspendRequestSchema, null);
        Type localVarReturnType = new TypeToken<UnSuspendResponseSchema>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Used to unsuspend one or more previously suspended Tokens. The API is limited to 10 Tokens per request. (asynchronously)
     * This API is used to unsuspend one or more previously suspended Tokens. The API is limited to 10 Tokens per request. MDES will coordinate the unsuspension of the Tokens and notify any relevant parties that the Tokens have now been unsuspended.
     *
     * @param unsuspendRequestSchema Contains the details of the request message.  (optional)
     * @param _callback              The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Contains the details of the response message.  </td><td>  -  </td></tr>
     * <tr><td> 0 </td><td> unexpected error  </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call createUnsuspendAsync(UnSuspendRequestSchema unsuspendRequestSchema, final ApiCallback<UnSuspendResponseSchema> _callback) throws ApiException {

        okhttp3.Call localVarCall = createUnsuspendValidateBeforeCall(unsuspendRequestSchema, _callback);
        Type localVarReturnType = new TypeToken<UnSuspendResponseSchema>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
