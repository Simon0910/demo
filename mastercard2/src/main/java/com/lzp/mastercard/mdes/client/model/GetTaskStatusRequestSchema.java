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


package com.lzp.mastercard.mdes.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * GetTaskStatusRequestSchema
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-16T09:54:27.990+01:00[Europe/London]")
public class GetTaskStatusRequestSchema {
    public static final String SERIALIZED_NAME_RESPONSE_HOST = "responseHost";
    @SerializedName(SERIALIZED_NAME_RESPONSE_HOST)
    private String responseHost;

    public static final String SERIALIZED_NAME_REQUEST_ID = "requestId";
    @SerializedName(SERIALIZED_NAME_REQUEST_ID)
    private String requestId;

    public static final String SERIALIZED_NAME_TOKEN_REQUESTOR_ID = "tokenRequestorId";
    @SerializedName(SERIALIZED_NAME_TOKEN_REQUESTOR_ID)
    private String tokenRequestorId;

    public static final String SERIALIZED_NAME_TASK_ID = "taskId";
    @SerializedName(SERIALIZED_NAME_TASK_ID)
    private String taskId;

    public GetTaskStatusRequestSchema responseHost(String responseHost) {
        this.responseHost = responseHost;
        return this;
    }

    /**
     * The host that originated the request. Future calls in the same conversation may be routed to this host.
     *
     * @return responseHost
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "site2.payment-app-provider.com", value = "The host that originated the request. Future calls in the same conversation may be routed to this host. ")
    public String getResponseHost() {
        return responseHost;
    }

    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public GetTaskStatusRequestSchema requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * Unique identifier for the request.
     *
     * @return requestId
     **/
    @ApiModelProperty(example = "123456", required = true, value = "Unique identifier for the request. ")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public GetTaskStatusRequestSchema tokenRequestorId(String tokenRequestorId) {
        this.tokenRequestorId = tokenRequestorId;
        return this;
    }

    /**
     * Identifies the Token Requestor.  __Length:11__
     *
     * @return tokenRequestorId
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "98765432101", value = "Identifies the Token Requestor.  __Length:11__ ")
    public String getTokenRequestorId() {
        return tokenRequestorId;
    }

    public void setTokenRequestorId(String tokenRequestorId) {
        this.tokenRequestorId = tokenRequestorId;
    }

    public GetTaskStatusRequestSchema taskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    /**
     * Unique identifier for this task. Must be an identifier previously used when requesting a task.    __Max Length:64__
     *
     * @return taskId
     **/
    @ApiModelProperty(example = "123456", required = true, value = "Unique identifier for this task. Must be an identifier previously used when requesting a task.    __Max Length:64__ ")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetTaskStatusRequestSchema getTaskStatusRequestSchema = (GetTaskStatusRequestSchema) o;
        return Objects.equals(this.responseHost, getTaskStatusRequestSchema.responseHost) &&
                Objects.equals(this.requestId, getTaskStatusRequestSchema.requestId) &&
                Objects.equals(this.tokenRequestorId, getTaskStatusRequestSchema.tokenRequestorId) &&
                Objects.equals(this.taskId, getTaskStatusRequestSchema.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, tokenRequestorId, taskId);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetTaskStatusRequestSchema {");
        sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("");
        sb.append("    requestId: ").append(toIndentedString(requestId)).append("");
        sb.append("    tokenRequestorId: ").append(toIndentedString(tokenRequestorId)).append("");
        sb.append("    taskId: ").append(toIndentedString(taskId)).append("");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("", "");
    }

}

